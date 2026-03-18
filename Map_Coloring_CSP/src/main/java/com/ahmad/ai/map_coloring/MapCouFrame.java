package com.ahmad.ai.map_coloring;

import javax.swing.*;
import java.awt.*;

public class MapCouFrame extends JFrame {
    private HelpFun engine;
    private JTextField txtCount = new JTextField(10);
    private JTextField txtResult = new JTextField(10);
    private JButton btnSolve = new JButton("Calculate Colors");
    private JButton btnDraw = new JButton("Visual Graph");

    public MapCouFrame() {
        setupUI();
        setTitle("AI Map Coloring Solver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 52, 54));

        btnSolve.addActionListener(e -> {
            try {
                engine = new HelpFun(Integer.parseInt(txtCount.getText()));
                engine.solve();
                txtResult.setText(String.valueOf(engine.resultColorCount));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number");
            }
        });

        btnDraw.addActionListener(e -> MySketch.launchSketch());

        panel.add(new JLabel("Number of Countries:", SwingConstants.CENTER));
        panel.add(txtCount);
        panel.add(btnSolve);
        panel.add(txtResult);
        panel.add(btnDraw);
        add(panel);
    }
}