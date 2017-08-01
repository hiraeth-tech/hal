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

/**
 *
 * @author Matteo Pariset
 */
public class Neuron {
    
    private final int type;
    private int synapses;
    private double[] weights;
    private double bias;
    private double[] inputs;
    private double output;
    
    public static final int LINEAR_RESPONSE = 0;
    public static final int NON_LINEAR_RESPONSE = 1;
    
    public Neuron(int type) {
        this.type = type;
        bias = 0;
    }
    
    public void createSynapses(int number) {
        synapses = number;
        weights = new double[number];
        for (int i = 0; i < number; i++) {
            weights[i] = Math.random() * 2d - 1d;
        }
    }
    
    public int getSynapses() {
        return synapses;
    }
    
    public double[] getWeights() {
        return weights;
    }
    
    public double getWeight(int originNeuron) {
        return weights[originNeuron];
    }
    
    public boolean input(double[] inputVector) {
        if (inputVector.length == weights.length) {
            inputs = inputVector;
            return true;
        }
        else {
            return false;
        }        
    }
    
    public double process() {
        double av = 0;
        for (int i = 0; i < weights.length; i++) {
            av += weights[i] * inputs[i];
        }
        
        switch(type) {
            case LINEAR_RESPONSE: //Linear response neuron
                break;
            case NON_LINEAR_RESPONSE: //Non-linear response neuron (logistic function)
                av = (1 / (1 + Math.exp(-av)));
                break;
        }
        
        output = av;
        
        return av;
    }
    
    public void learn(double learningRate, double sensitivity) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * sensitivity * output;
        }
    }
    
}
