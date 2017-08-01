/*
 * The MIT License
 *
 * Copyright 2017 Matteo Pariset.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package brain;

import java.util.Arrays;
import javax.swing.SwingUtilities;

import hal.Hal;

/**
 *
 * @author Matteo Pariset
 */
public class VanillaNetwork {
    
    private int inputDimension;
    private int layersNumber;
    private int biggestLayerComposition;
    private int[] networkTopology;
    private Neuron[][] neurons;
    private double[][] output;
    private double[][] sensitivity;
    private final double learningRate;
    private final double sensitivityThreshold;
    private int learningCycles;
    
    public boolean isNewborn;
    
    //calcLastLayerSensitivity()
    boolean lastAcceptability;
    
    //calcHiddenLayersSensitivity()
    double backwardContributions;
    boolean hiddenAcceptability;
    
    public VanillaNetwork(double learningRate, double sensitivityThreshold) {
        this.learningRate = learningRate;
        this.sensitivityThreshold = sensitivityThreshold;
    }
    
    public void initBrain(int inputNumber, int[] layersComposition, int learningCycles)  {
        
        isNewborn = true;
        inputDimension = inputNumber;
        layersNumber = layersComposition.length;
        networkTopology = layersComposition;
        biggestLayerComposition = 0;
        for (int i = 0; i < layersNumber; i++) {
            if (biggestLayerComposition < layersComposition[i]) {
               biggestLayerComposition = layersComposition[i];
            }
        }
        this.learningCycles = learningCycles;
        
        neurons = new Neuron[layersNumber][biggestLayerComposition];
        
        sensitivity = new double[layersNumber][biggestLayerComposition];
        
        for (int j = 0; j < layersComposition[0]; j++) {
            neurons[0][j] = new Neuron(Neuron.NON_LINEAR_RESPONSE);
            neurons[0][j].createSynapses(inputDimension);
        }
        
        for (int layer = 1; layer < layersNumber; layer++) {
            for (int j = 0; j < biggestLayerComposition; j++) {
                neurons[layer][j] = new Neuron(Neuron.NON_LINEAR_RESPONSE);
                neurons[layer][j].createSynapses(layersComposition[layer - 1]);
            }
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hal.pet.updateAnswer("...");
            }
        });
        
    }
    
    public double getNeuronWeight(int layer, int neuron, int origin) {
        return neurons[layer][neuron].getWeight(origin);
    }
    
    public double[] think(double[] input) {
        double[] info;
        double[] localOutput;
        info = input;
        output = new double[layersNumber][biggestLayerComposition];
        
        for (int layer = 0; layer < layersNumber; layer++) {
            localOutput = new double[networkTopology[layer]];
            for (int j = 0; j < networkTopology[layer]; j++) {
                neurons[layer][j].input(info);
                localOutput[j] = neurons[layer][j].process();
                output[layer][j] = localOutput[j];
            }
            info = localOutput;
        }
        
        return output[layersNumber - 1];
    }
    
    private String getAnswer() {
        
        if (isNewborn) {
            return "I don't know";
        }
        else if (output[layersNumber - 1][0] > (output[layersNumber - 1][1] + 0.21d)) {
            return "That's a P";
        }
        else if ((output[layersNumber - 1][0] + 0.21d) < (output[layersNumber - 1][1])) {
            return "That's a T";
        }
        else {
            return "Both";
        }
    }
    
    public void printAnswer() {
        final String answer = getAnswer();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hal.pet.updateAnswer(answer);
            }
        });
    }
    
    public void school(double[][] inputs, double[][] targets, int trainingSamples) {
        boolean isReady = false;
        int cycle = 0;
        int chosenSample;
        
        Hal.printConsole("Learning, please wait...");
        
        try {
            do {
                chosenSample = (int) Math.round(Math.random() * (trainingSamples - 1));
                think(inputs[chosenSample]);
                calcLastLayerSensitivity(targets[chosenSample]);
                calcHiddenLayersSensitivity();
                for (int layer = 0; layer < layersNumber; layer++) {
                    for (int neuron = 0; neuron < networkTopology[layer]; neuron++) {
                        //Hal.printConsole("layer " + layer + "/neuron " + neuron + ": " + Arrays.toString(neurons[layer][neuron].getWeights()));
                        neurons[layer][neuron].learn(learningRate, sensitivity[layer][neuron]);
                    }
                }
                
                /*
                for (int sample = 0; sample < trainingSamples; sample++) {
                    think(inputs[sample]);
                    isReady = isReady && calcGradient(targets[sample]);
                }
                */

                if (cycle >= learningCycles) {
                    isReady = true;
                    Hal.printConsole("Learning completed");
                }

                //System.out.println("EPOCH " + cycle + ": " + Arrays.toString(output[layersNumber - 1]));
                
                if (Hal.evolutionShown) {
                    Hal.printConsole("l " + 0 + "/n " + 7 + ": " + Arrays.toString(neurons[0][7].getWeights()));

                    if ((cycle % 10) == 0) {
                        Hal.printConsole("EPOCH " + cycle + ": " + Arrays.toString(output[layersNumber - 1]));
                        updateGraph();
                    }

                    Thread.sleep(1);
                }

                cycle++;
            } while (!isReady);
            
            isNewborn = false;

        } catch (InterruptedException e) {
            
        }
    }
    
    public void updateGraph() {
        SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    Hal.pet.updateGraph(output, neurons);
                }
        });
    }
    
    private boolean calcLastLayerSensitivity(double[] target) {
        lastAcceptability = true;
        for (int neuron = 0; neuron < networkTopology[layersNumber - 1]; neuron++) {
            sensitivity[layersNumber - 1][neuron] = output[layersNumber - 1][neuron] * (1 - output[layersNumber - 1][neuron]) * (target[neuron] - output[layersNumber - 1][neuron]);
            if (Math.abs(sensitivity[layersNumber - 1][neuron]) > sensitivityThreshold) {
                lastAcceptability = false;
            }
        }
        return lastAcceptability;
    }
    
    private boolean calcGradient(double[] target) {
        boolean acceptability = true;
        for (int neuron = 0; neuron < networkTopology[layersNumber - 1]; neuron++) {
            if (Math.abs(output[layersNumber - 1][neuron] * output[layersNumber - 1][neuron] * (1 - output[layersNumber - 1][neuron]) * (target[neuron] - output[layersNumber - 1][neuron])) > sensitivityThreshold) {
                acceptability = false;
            }
            //Hal.printConsole("N->" + neuron + " " + acceptability + " ");
        }
        return acceptability;
    }
    
    private void calcHiddenLayersSensitivity() {
        hiddenAcceptability = true;
        for (int layer = (layersNumber - 2); layer >= 0; layer--) {
            for (int neuron = 0; neuron < networkTopology[layer]; neuron++) {
                backwardContributions = 0;
                for (int i = 0; i < networkTopology[layer + 1]; i++) {
                    backwardContributions += sensitivity[layer + 1][i] * neurons[layer + 1][i].getWeight(neuron);
                }
                sensitivity[layer][neuron] = output[layer][neuron] * (1 - output[layer][neuron]) * backwardContributions;
            }
        }
    }
    
}
