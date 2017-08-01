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

import brain.Neuron;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Arrays;
import javax.swing.text.DefaultCaret;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author Matteo Pariset
 */
public class PET extends JFrame {
    
    /*
    private static final int[][] PATTERN_P = new int[][]{
        {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
    };
    
    private static final int[][] P1 = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    
    private static final int[][] P2 = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
    };
    
    private static final int[][] PATTERN_T = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    };    
    
    private static final int[][] T1 = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    };
    
    private static final int[][] T2 = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    
    private static final double[] TARGET_P = new double[]{1d, 0d};
    
    private static final double[] TARGET_T = new double[]{0d, 1d};
    
    */
    
    private ActionPanel inputPanel;
    private JPanel outputPanel;
    private JPanel inputContainer;
    private JPanel outputContainer;
    private JPanel cmdContainer;
    private JPanel consoleContainer;
    private JPanel brainContainer;
    private JScrollPane jScrollPane1;
    private JTextArea consoleArea;
    private JToggleButton autoLayoutButton;
    private JPanel answerPanel;
    
    private JButton drawT;
    private JButton drawP;
    private JButton fullButton;
    private JButton trainButton;
    private JButton thinkButton;
    private JButton resetButton;
    private JTextField neuronField;
    private JTextField learningRateField;
    private JTextField gradientThresholdField;
    private JTextField learningCyclesField;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    
    private JLabel networkAnswer;
    
    private JCheckBox evolutionCheckBox;
    
    Graph graph;
    Viewer graphViewer;
    ViewPanel graphView;
    boolean isAutoLayout = false;
    
    public static int[][] input;
    public int neurons0Layer;
    public double[] target;
    
    private float tempEdgeSize;
    
    public PET() { 
        
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        */
        
        input = getFreshP(0);
        
    }
    
    public void init() {
        
        inputContainer = new javax.swing.JPanel();
        inputPanel = new ActionPanel();
        cmdContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleArea = new javax.swing.JTextArea();
        consoleContainer = new javax.swing.JPanel();
        brainContainer = new javax.swing.JPanel();
        outputPanel = new javax.swing.JPanel();
        drawT = new JButton();
        drawP = new JButton();
        fullButton = new JButton();
        trainButton = new JButton();
        thinkButton = new JButton();
        resetButton = new JButton();
        neuronField = new JTextField();
        learningRateField = new JTextField();
        gradientThresholdField = new JTextField();
        learningCyclesField = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        autoLayoutButton = new JToggleButton();
        evolutionCheckBox = new JCheckBox();
        answerPanel = new JPanel();
        networkAnswer = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hal");
        setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        setResizable(false);
        
        DefaultCaret caret = (DefaultCaret) consoleArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        target = getTargetP();
        
        this.addMouseListener(inputPanel);
        inputContainer.addMouseListener(inputPanel);

        inputContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 16))); // NOI18N
        inputContainer.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N

        inputPanel.setBackground(new java.awt.Color(255, 255, 255));
        inputPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inputPanel.setName("inputPanel"); // NOI18N
        inputPanel.setPreferredSize(new java.awt.Dimension(301, 301));

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        drawT.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        drawT.setText("Draw T");
        drawT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawTActionPerformed(evt);
            }
        });

        drawP.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        drawP.setText("Draw P");
        drawP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawPActionPerformed(evt);
            }
        });

        fullButton.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        fullButton.setText("Fill");
        fullButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fullButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullButtonActionPerformed(evt);
            }
        });

        thinkButton.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        thinkButton.setText("Think");
        thinkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thinkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputContainerLayout = new javax.swing.GroupLayout(inputContainer);
        inputContainer.setLayout(inputContainerLayout);
        inputContainerLayout.setHorizontalGroup(
            inputContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(inputContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputContainerLayout.createSequentialGroup()
                        .addComponent(drawP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputContainerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fullButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(thinkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        inputContainerLayout.setVerticalGroup(
            inputContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(inputContainerLayout.createSequentialGroup()
                        .addGroup(inputContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drawP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drawT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(thinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmdContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Command Line", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 16))); // NOI18N

        consoleArea.setColumns(20);
        consoleArea.setRows(5);
        consoleArea.setName("cmdPanel"); // NOI18N
        jScrollPane1.setViewportView(consoleArea);

        evolutionCheckBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        evolutionCheckBox.setText("Show evolution");
        evolutionCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evolutionCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cmdContainerLayout = new javax.swing.GroupLayout(cmdContainer);
        cmdContainer.setLayout(cmdContainerLayout);
        cmdContainerLayout.setHorizontalGroup(
            cmdContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmdContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cmdContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evolutionCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cmdContainerLayout.setVerticalGroup(
            cmdContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(evolutionCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        consoleContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Console", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 16))); // NOI18N

        neuronField.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        neuronField.setText("80");
        neuronField.setToolTipText("");
        neuronField.setMargin(new java.awt.Insets(2, 10, 2, 10));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("# Neurons");

        learningRateField.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        learningRateField.setText("0.15");
        learningRateField.setToolTipText("");
        learningRateField.setMargin(new java.awt.Insets(2, 10, 2, 10));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Learning Rate");

        gradientThresholdField.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        gradientThresholdField.setText("0.005");
        gradientThresholdField.setToolTipText("");
        gradientThresholdField.setMargin(new java.awt.Insets(2, 10, 2, 10));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Min gradient");

        learningCyclesField.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        learningCyclesField.setText("50000");
        learningCyclesField.setToolTipText("");
        learningCyclesField.setMargin(new java.awt.Insets(2, 10, 2, 10));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Learnig cycles");

        trainButton.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        trainButton.setText("Train brain");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        resetButton.setText("Create brain");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        autoLayoutButton.setFont(new java.awt.Font("Calibri Light", 0, 16)); // NOI18N
        autoLayoutButton.setText("AutoLayout");
        autoLayoutButton.setPreferredSize(new java.awt.Dimension(119, 29));
        autoLayoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoLayoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout consoleContainerLayout = new javax.swing.GroupLayout(consoleContainer);
        consoleContainer.setLayout(consoleContainerLayout);
        consoleContainerLayout.setHorizontalGroup(
            consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consoleContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(learningRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(neuronField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(learningCyclesField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(gradientThresholdField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consoleContainerLayout.createSequentialGroup()
                        .addComponent(autoLayoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(trainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        consoleContainerLayout.setVerticalGroup(
            consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consoleContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(autoLayoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consoleContainerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neuronField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consoleContainerLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(learningRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(consoleContainerLayout.createSequentialGroup()
                        .addComponent(trainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consoleContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(consoleContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, consoleContainerLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(gradientThresholdField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(learningCyclesField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        brainContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brain", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 16))); // NOI18N

        outputPanel.setBackground(new java.awt.Color(255, 255, 255));
        outputPanel.setName("brainPanel"); // NOI18N

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout brainContainerLayout = new javax.swing.GroupLayout(brainContainer);
        brainContainer.setLayout(brainContainerLayout);
        brainContainerLayout.setHorizontalGroup(
            brainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brainContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        brainContainerLayout.setVerticalGroup(
            brainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        answerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Answer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 16))); // NOI18N

        networkAnswer.setFont(new java.awt.Font("Consolas", 0, 48)); // NOI18N
        networkAnswer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        networkAnswer.setText("...");

        javax.swing.GroupLayout answerPanelLayout = new javax.swing.GroupLayout(answerPanel);
        answerPanel.setLayout(answerPanelLayout);
        answerPanelLayout.setHorizontalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(networkAnswer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        answerPanelLayout.setVerticalGroup(
            answerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(answerPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(networkAnswer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(answerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(consoleContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(brainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consoleContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(brainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        
        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.ico")).getImage());
        
        evolutionCheckBox.setSelected(true);
        
        inputPanel.repaint();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private static double[] getTargetP() {
        return new double[]{1, 0};
    }
    
    private static double[] getTargetT() {
        return new double[]{0, 1};
    }
    
    private static int[][] getFreshT(int specimen) {
        int[][] suitable;
        switch (specimen) {
            case 0:
                suitable = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                };
                break;
            case 1:
                suitable = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                };
                break;
            case 2:
                suitable = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                };
                break;
            default:
                suitable = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                };
        }
        return suitable;
    }
    
    public static int[][] getFreshP(int specimen) {
        int[][] suitable;
        switch (specimen) {
            case 0:
                suitable = new int[][]{
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                };
                break;
            case 1:
                suitable = new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {1, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                };
                break;
            case 2:
                suitable = new int[][]{
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                    {0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                };
                break;
            default:
                suitable = new int[][]{
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                };
        }
        
        return suitable;
    }
    
    public void drawTActionPerformed(ActionEvent e) {
        input = getFreshT(0);
        inputPanel.repaint();
    }
    
    public void drawPActionPerformed(ActionEvent e) {
        input = getFreshP(0);
        inputPanel.repaint();
    }
    
    public void fullButtonActionPerformed(ActionEvent e) {
        input = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        inputPanel.repaint();
    }
    
    public void twistButtonActionPerformed(ActionEvent e) {
        
    }
    
    public void trainButtonActionPerformed(ActionEvent e) {
        Hal.inputs = new double[2][100];
        Hal.targets = new double[][]{{1, 0}, {0, 1}};
        //Hal.targets = new double[][]{{1, 0}, {1, 0}, {1, 0}, {0, 1}, {0, 1}, {0, 1}};
        input = getFreshP(0);
        Hal.inputs[0] = get1dInput();
        /*
        input = getFreshP(1);
        Hal.inputs[1] = get1dInput();
        input = getFreshP(2);
        Hal.inputs[2] = get1dInput();
        */
        input = getFreshT(0);
        Hal.inputs[1] = get1dInput();
        /*
        input = getFreshT(1);
        Hal.inputs[4] = get1dInput();
        input = getFreshT(2);
        Hal.inputs[5] = get1dInput();
        /*
        Hal.input = get1dInput();
        Hal.target = target;
        */
        
        Hal.getToSchool = true;
        
        input = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        inputPanel.repaint();
        
    }
    
    public void thinkButtonActionPerformed(ActionEvent e) {
        Hal.printConsole("Asking a question?");
        Hal.printConsole(Arrays.toString(input[0]));
        Hal.printConsole(Arrays.toString(input[1]));
        Hal.printConsole(Arrays.toString(input[2]));
        Hal.printConsole(Arrays.toString(input[3]));
        Hal.printConsole(Arrays.toString(input[4]));
        Hal.printConsole(Arrays.toString(input[5]));
        Hal.printConsole(Arrays.toString(input[6]));
        Hal.printConsole(Arrays.toString(input[7]));
        Hal.printConsole(Arrays.toString(input[8]));
        Hal.printConsole(Arrays.toString(input[9]));
        
        Hal.getInput = true;
        while (!Hal.inputLoaded) {
            
        }
        Hal.inputLoaded = false;
        Hal.goToWork = true;
    }
    
    public void evolutionCheckBoxActionPerformed(ActionEvent e) {
        Hal.evolutionShown = evolutionCheckBox.isSelected();
    }
    
    public void autoLayoutButtonActionPerformed(ActionEvent e) {
        isAutoLayout = !isAutoLayout;
    }
    
    public void resetButtonActionPerformed(ActionEvent e) {
        neurons0Layer = Integer.parseInt(neuronField.getText());
        Hal.learningRate = Double.parseDouble(learningRateField.getText());
        Hal.gradientThreshold = Double.parseDouble(gradientThresholdField.getText());
        Hal.learningCycles = Integer.parseInt(learningCyclesField.getText());
        Hal.layer0Neurons = neurons0Layer;
        Hal.printConsole("Network parameters chosen: -Neurons=" + neurons0Layer + " -Learning Rate=" + Double.parseDouble(learningRateField.getText()) + " -GradientThreshold=" + Double.parseDouble(gradientThresholdField.getText()) + "-LearningCycles=" + learningCyclesField.getText());
        Hal.createBrain = true;
        while(!Hal.brainReady) {
            
        }
        Hal.brainReady = false;
        drawGraph();
    }
    
    public static double[] get1dInput() {
        double[] input1d = new double[input.length * input.length];
        int rowSize = input.length;
        int column;
        int row = 0;
        for (int i = 0; i < input1d.length; i++) {
            if (i == ((row + 1) * rowSize)) {
                row += 1;
            }
            column = i - row * rowSize;
            input1d[i] = (double) input[row][column];
        }
        return input1d;
    }
    
    public void drawGraph() {
        
        if (graphViewer != null) {
            graphViewer.close();
        }
        
        outputPanel.removeAll();
        graph = new MultiGraph("Brain");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet", "url(file:///C:/Users/Matteo/Programs/Projects/Alan/resources/graphstream.css)");
        
        double[] input1d = get1dInput();
        
        for (int i = 0; i < 10; i++) {
            graph.addNode("I" + i).setAttribute("xyz", 0, 20 * i, 0);
        }
        
        double size;
        
        for (int i = 0; i < neurons0Layer; i++) {
            graph.addNode("0L" + i).setAttribute("xyz", (i % 5) * 70 + 100, 7 * i, 2);
            graph.getNode("0L" + i).setAttribute("ui.class", "hiddenLayers");
            graph.getNode("0L" + i).setAttribute("ui.color", 0);
            for (int j = 0; j < 10; j++) {
                size = Hal.brain.getNeuronWeight(0, i, j) < 0 ? (-Hal.brain.getNeuronWeight(0, i, j) * 6.2) : (Hal.brain.getNeuronWeight(0, i, j) * 6.2);
                graph.addEdge("EI-" + j + "-0-" + i, "I" + j, "0L" + i).setAttribute("ui.size", ((int) (size)));
            }
        }
        
        graph.addNode("1L0").setAttribute("ui.class", "outputLayers");
        graph.getNode("1L0").setAttribute("xyz", 600, 50, 10);
        graph.addNode("1L1").setAttribute("ui.class", "outputLayers");
        graph.getNode("1L1").setAttribute("xyz", 600, 200, 10);
        
        for (int i = 0; i < neurons0Layer; i++) {
            size = Hal.brain.getNeuronWeight(1, 0, i) < 0 ? (-Hal.brain.getNeuronWeight(1, 0, i) * 6.2) : (Hal.brain.getNeuronWeight(1, 0, i) * 6.2);
            graph.addEdge("E0-" + i + "-1-0", "0L" + i, "1L0").setAttribute("ui.size", ((int) (size)));
            size = Hal.brain.getNeuronWeight(1, 1, i) < 0 ? (-Hal.brain.getNeuronWeight(1, 1, i) * 6.2) : (Hal.brain.getNeuronWeight(1, 1, i) * 6.2);
            graph.addEdge("E0-" + i + "-1-1", "0L" + i, "1L1").setAttribute("ui.size", ((int) (size)));
        }
        
        graphViewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        
        if (isAutoLayout) {
            graphViewer.enableAutoLayout();
        }
        graphView = graphViewer.addDefaultView(false);
        graphView.setBounds(0, 0, 916, 465);
        outputPanel.add(graphView);
    }
    
    public void updateGraph(double[][] output, Neuron[][] neurons) {
        for (int i = 0; i < neurons0Layer; i++) {
            graph.getNode("0L" + i).setAttribute("ui.color", output[0][i]);
            for (int synapse = 0; synapse < 10; synapse++) {
                tempEdgeSize = (float) neurons[0][i].getWeight(10 * synapse);
                tempEdgeSize = tempEdgeSize < 0 ? (-tempEdgeSize * 6.2f) : (tempEdgeSize * 6.2f);
                graph.getEdge("EI-" + synapse + "-0-" + i).setAttribute("ui.size", tempEdgeSize);
            }
        }
        graph.getNode("1L0").setAttribute("ui.color", output[1][0]);
        graph.getNode("1L0").setAttribute("label", Math.round(output[1][0] * 1000.0) / 1000.0);
        graph.getNode("1L1").setAttribute("ui.color", output[1][1]);
        graph.getNode("1L1").setAttribute("label", Math.round(output[1][1] * 1000.0) / 1000.0);
        
        for (int i = 0; i < neurons0Layer; i++) {
            tempEdgeSize = (float) neurons[1][0].getWeight(i);
            tempEdgeSize = tempEdgeSize < 0 ? (-tempEdgeSize * 6.2f) : (tempEdgeSize * 6.2f);
        }
    }
    
    public void updateConsole(String message) {
        if (consoleArea.getLineCount() > 3000) {
            consoleArea.setText("");
        }
        consoleArea.append(message + "\r\n");
    }
    
    public void updateAnswer(String answer) {
        networkAnswer.setText(answer);
    }
    
    class ActionPanel extends JPanel implements MouseListener {
        
        static final int PIXEL_SIZE = 30;
        
        @Override
        public void paintComponent(Graphics gg) {
            super.paintComponent(gg);
            Graphics g = gg;
            for (int row = 0; row < input.length; row++) {
                for (int column = 0; column < input.length; column++) {
                    if (input[row][column] == 1) {
                        g.setColor(Color.GRAY);
                    }
                    else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(PIXEL_SIZE * column, PIXEL_SIZE * row, PIXEL_SIZE, PIXEL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(PIXEL_SIZE * column, PIXEL_SIZE * row, PIXEL_SIZE, PIXEL_SIZE);
                }
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            float x = e.getX() - 18; //Correction FIXME
            float y = e.getY() - 35; //Correction FIXME
            
            if (x < 300 && y < 300) {
                int row = (int) Math.round(Math.floor(y / PIXEL_SIZE));
                int column = (int) Math.round(Math.floor(x / PIXEL_SIZE));
                
                if (input[row][column] == 1) {
                    input[row][column] = 0;
                }
                else {
                    input[row][column] = 1;
                }
                
                inputPanel.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }
    }
}
