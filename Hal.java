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

package hal;

import brain.VanillaNetwork;
import java.util.Arrays;
import javax.swing.SwingUtilities;


/**
 *
 * @author Matteo Pariset
 */
        
public class Hal {
    
    public static VanillaNetwork brain;
    
    public static volatile boolean createBrain = false;
    public static volatile boolean brainReady = false;
    public static volatile boolean getToSchool = false;
    public static volatile boolean goToWork = false;
    public static volatile boolean getInput = false;
    public static volatile boolean inputLoaded = false;
    public static volatile boolean evolutionShown = true;
    
    public static volatile double learningRate;
    public static volatile double gradientThreshold;
    public static volatile int layer0Neurons;
    public static volatile int learningCycles;
    
    public static double[] input;
    public static double[] target;
    
    public static double[][] inputs;
    public static double[][] targets;
    
    public static PET pet;
    
    public static void main(String[] args) {
        
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        
        /*
        VanillaNetwork brain = new VanillaNetwork(0.02, 0.005);
        brain.initBrain(49, new int[]{18, 2});
        brain.school(new double[]{1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1}, new double[]{1, 0});
        
        System.out.println("Twinsting original...");
        
        double[] response = brain.think(new double[]{1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1});
        
        System.out.println("Answer: " + Arrays.toString(response));
        */
        
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                pet = new PET();
                pet.init();
            }
        });
        
        while(true) {
            if (getInput) {
                //printConsole("Loading input...");
                getInput = false;
                Hal.input = PET.get1dInput();
                inputLoaded = true;
            }
            
            if (createBrain) {
                //printConsole("Creating new brain...");
                createBrain = false;
                initBrain();
                printConsole("Brain ready");
                brainReady = true;
            }
            
            if (getToSchool) {
                //printConsole("Starting school");
                getToSchool = false;
                brain.school(inputs, targets, 2);
                printConsole("Ok, now I know everything!");
            }
            
            if (goToWork) {
                //printConsole("Thinking...");
                if (brain != null) {
                    goToWork = false;
                    printConsole("My answer: " + Arrays.toString(brain.think(input)));
                    brain.printAnswer();
                    brain.updateGraph();
                }
            }
        }
    }
    
    
    public static void printConsole(final String message) {
        pet.updateConsole(message);
    }
    
    
    public static void initBrain() {
        brain = new VanillaNetwork(learningRate, gradientThreshold);
        brain.initBrain(100, new int[]{layer0Neurons, 2}, Hal.learningCycles);
    }
    
}
