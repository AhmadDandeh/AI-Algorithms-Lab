package com.ahmad.ai.xo;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private GameEngine engine = new GameEngine();
    private int[][] board = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
    private int playerSelection = 0; // 0: X, 1: O
    private int aiTurnHelper = 0;
    private JButton[] buttons = new JButton[9];
    private JLabel statusLabel = new JLabel("Select turn to start", SwingConstants.CENTER);
    private JRadioButton xFirst = new JRadioButton("Play first with X");
    private JRadioButton oFirst = new JRadioButton("Play second with O");

    public MainForm() {
        initComponents();
        setTitle("AI XO - Alpha Beta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBackground(new Color(0, 153, 204));

        for (int i = 0; i < 9; i++) {
            final int idx = i;
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].addActionListener(e -> playerMove(idx));
            gridPanel.add(buttons[i]);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        group.add(xFirst); group.add(oFirst);

        xFirst.addActionListener(e -> startNewGame(0));
        oFirst.addActionListener(e -> startNewGame(1));

        JButton restartBtn = new JButton("Restart Game");
        restartBtn.addActionListener(e -> startNewGame(playerSelection));

        controlPanel.add(xFirst); controlPanel.add(oFirst);
        controlPanel.add(restartBtn);

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void playerMove(int idx) {
        int r = idx / 3, c = idx % 3;
        if (!buttons[idx].getText().isEmpty() || engine.isGameOver(board)) return;

        // User Move
        board[r][c] = playerSelection;
        buttons[idx].setText(playerSelection == 0 ? "X" : "O");

        if (!engine.isGameOver(board)) {
            // AI Move
            engine.alphaBeta(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, (playerSelection + 1) % 2, 0, aiTurnHelper);
            board[engine.bestX][engine.bestY] = (playerSelection + 1) % 2;
            buttons[engine.bestX * 3 + engine.bestY].setText(playerSelection == 0 ? "O" : "X");
        }
        updateStatus();
    }

    private void updateStatus() {
        if (engine.isGameOver(board)) {
            if (engine.hasXWon(board)) statusLabel.setText(playerSelection == 0 ? "You Win!" : "AI Wins!");
            else if (engine.hasOWon(board)) statusLabel.setText(playerSelection == 1 ? "You Win!" : "AI Wins!");
            else statusLabel.setText("It's a Draw!");
        }
    }

    private void startNewGame(int selection) {
        this.playerSelection = selection;
        this.aiTurnHelper = selection;
        board = new int[][]{{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
        for (JButton b : buttons) b.setText("");
        statusLabel.setText("Your turn!");

        if (playerSelection == 1) { // AI starts
            int rx = (int)(Math.random()*3), ry = (int)(Math.random()*3);
            board[rx][ry] = 0;
            buttons[rx*3 + ry].setText("X");
        }
    }
}


/*
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainForm extends JFrame {
    GameEngine a1 = new GameEngine();
    public int[][] board = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
    private int alpha = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;
    public int turn = 0;
    public int player;
    public int x = 0;
    public int helpper;
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;

    public MainForm() {
        this.initComponents();
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jPanel2 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jButton9 = new JButton();
        this.jPanel3 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jPanel4 = new JPanel();
        this.jButton10 = new JButton();
        this.jRadioButton1 = new JRadioButton();
        this.jRadioButton2 = new JRadioButton();
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jPanel1.setMaximumSize(new Dimension(432, 217));
        this.jPanel2.setBackground(new Color(0, 153, 204));
        this.jButton1.setBackground(new Color(255, 255, 255));
        this.jButton1.addActionListener(new 1(this));
        this.jButton2.setBackground(new Color(255, 255, 255));
        this.jButton2.addActionListener(new 2(this));
        this.jButton3.setBackground(new Color(255, 255, 255));
        this.jButton3.addActionListener(new 3(this));
        this.jButton4.setBackground(new Color(255, 255, 255));
        this.jButton4.addActionListener(new 4(this));
        this.jButton5.setBackground(new Color(255, 255, 255));
        this.jButton5.addActionListener(new 5(this));
        this.jButton6.setBackground(new Color(255, 255, 255));
        this.jButton6.addActionListener(new 6(this));
        this.jButton7.setBackground(new Color(255, 255, 255));
        this.jButton7.addActionListener(new 7(this));
        this.jButton8.setBackground(new Color(255, 255, 255));
        this.jButton8.addActionListener(new 8(this));
        this.jButton9.setBackground(new Color(255, 255, 255));
        this.jButton9.addActionListener(new 9(this));
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(this.jButton1, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton2, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton3, -2, 50, -2).addGap(0, 0, 32767)).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addGap(0, 0, 32767).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(this.jButton4, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton5, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton6, -2, 50, -2)).addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(this.jButton7, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton8, -2, 50, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton9, -2, 50, -2))))).addContainerGap(-1, 32767)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton3, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton2, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton1, Alignment.TRAILING, -2, 40, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton5, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton4, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton6, Alignment.TRAILING, -2, 40, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton9, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton8, Alignment.TRAILING, -2, 40, -2).addComponent(this.jButton7, Alignment.TRAILING, -2, 40, -2)).addContainerGap()));
        this.jPanel3.setBackground(new Color(0, 153, 204));
        this.jLabel1.setForeground(new Color(255, 255, 255));
        this.jLabel1.setHorizontalAlignment(0);
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -1, -1, 32767).addContainerGap()));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -1, 35, 32767).addContainerGap()));
        this.jPanel4.setBackground(new Color(0, 153, 204));
        this.jButton10.setText("Restart the Game");
        this.jButton10.addActionListener(new 10(this));
        this.jRadioButton1.setBackground(new Color(255, 255, 255));
        this.jRadioButton1.setText("Play first with X");
        this.jRadioButton1.addActionListener(new 11(this));
        this.jRadioButton2.setBackground(new Color(255, 255, 255));
        this.jRadioButton2.setText("Play second with O");
        this.jRadioButton2.addActionListener(new 12(this));
        GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
        this.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton10, -1, -1, 32767).addContainerGap()).addGroup(Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addContainerGap(51, 32767).addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jRadioButton1, -1, -1, 32767).addComponent(this.jRadioButton2, -1, 163, 32767)).addGap(28, 28, 28)));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addGap(32, 32, 32).addComponent(this.jRadioButton1).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jRadioButton2).addGap(18, 18, 18).addComponent(this.jButton10, -1, -1, 32767).addContainerGap()));
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jPanel4, -2, -1, -2)).addComponent(this.jPanel3, Alignment.TRAILING, -1, -1, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel4, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel3, -2, -1, -2)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2));
        this.pack();
    }

    public void resetBoard() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.board[i][j] = -1;
            }
        }

    }

    public void destroy() {
        if (this.jButton1.getText() == "") {
            this.jButton1.setText((String)null);
        }

        if (this.jButton2.getText() == "") {
            this.jButton2.setText((String)null);
        }

        if (this.jButton3.getText() == "") {
            this.jButton3.setText((String)null);
        }

        if (this.jButton4.getText() == "") {
            this.jButton4.setText((String)null);
        }

        if (this.jButton5.getText() == "") {
            this.jButton5.setText((String)null);
        }

        if (this.jButton6.getText() == "") {
            this.jButton6.setText((String)null);
        }

        if (this.jButton7.getText() == "") {
            this.jButton7.setText((String)null);
        }

        if (this.jButton8.getText() == "") {
            this.jButton8.setText((String)null);
        }

        if (this.jButton9.getText() == "") {
            this.jButton9.setText((String)null);
        }

    }

    public void helpButton(int x, int y, String s) {
        if (x == 0) {
            if (y == 0) {
                this.jButton1.setText(s);
            } else if (y == 1) {
                this.jButton2.setText(s);
            } else if (y == 2) {
                this.jButton3.setText(s);
            }
        } else if (x == 1) {
            if (y == 0) {
                this.jButton4.setText(s);
            } else if (y == 1) {
                this.jButton5.setText(s);
            } else if (y == 2) {
                this.jButton6.setText(s);
            }
        } else if (x == 2) {
            if (y == 0) {
                this.jButton7.setText(s);
            } else if (y == 1) {
                this.jButton8.setText(s);
            } else if (y == 2) {
                this.jButton9.setText(s);
            }
        }

    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (this.jButton1.getText() == "") {
            if (this.player == 0) {
                this.board[0][0] = 0;
                this.jButton1.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[0][0] = 1;
                this.jButton1.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        if (this.jButton2.getText() == "") {
            if (this.player == 0) {
                this.board[0][1] = 0;
                this.jButton2.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[0][1] = 1;
                this.jButton2.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        if (this.jButton3.getText() == "") {
            if (this.player == 0) {
                this.board[0][2] = 0;
                this.jButton3.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[0][2] = 1;
                this.jButton3.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        if (this.jButton4.getText() == "") {
            if (this.player == 0) {
                this.board[1][0] = 0;
                this.jButton4.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[1][0] = 1;
                this.jButton4.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        if (this.jButton5.getText() == "") {
            if (this.player == 0) {
                this.board[1][1] = 0;
                this.jButton5.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[1][1] = 1;
                this.jButton5.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        if (this.jButton6.getText() == "") {
            if (this.player == 0) {
                this.board[1][2] = 0;
                this.jButton6.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[1][2] = 1;
                this.jButton6.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        if (this.jButton7.getText() == "") {
            if (this.player == 0) {
                this.board[2][0] = 0;
                this.jButton7.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[2][0] = 1;
                this.jButton7.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        if (this.jButton8.getText() == "") {
            if (this.player == 0) {
                this.board[2][1] = 0;
                this.jButton8.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[2][1] = 1;
                this.jButton8.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton9ActionPerformed(ActionEvent evt) {
        if (this.jButton9.getText() == "") {
            if (this.player == 0) {
                this.board[2][2] = 0;
                this.jButton9.setText("X");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "O");
                this.board[this.a1.bestX][this.a1.bestY] = 1;
            } else if (this.player == 1) {
                this.board[2][2] = 1;
                this.jButton9.setText("O");
                this.a1.alpha_beta_fun(this.board, this.alpha, this.beta, this.turn, (this.player + 1) % 2, this.x, this.helpper);
                this.helpButton(this.a1.bestX, this.a1.bestY, "X");
                this.board[this.a1.bestX][this.a1.bestY] = 0;
            }
        }

        if (this.a1.isGameOver(this.board)) {
            this.destroy();
            if (this.a1.hasXWon(this.board) && this.player == 0) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else if (this.a1.hasXWon(this.board) && this.player == 1) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 0) {
                this.jLabel1.setText("Unfortunately, you lost!");
            } else if (this.a1.hasOWon(this.board) && this.player == 1) {
                this.jLabel1.setText("You Win!!!!!!!");
            } else {
                this.jLabel1.setText("It's a draw!");
            }
        }

    }

    private void jButton10ActionPerformed(ActionEvent evt) {
        this.resetBoard();
        this.jButton1.setText("");
        this.jButton2.setText("");
        this.jButton3.setText("");
        this.jButton4.setText("");
        this.jButton5.setText("");
        this.jButton6.setText("");
        this.jButton7.setText("");
        this.jButton8.setText("");
        this.jButton9.setText("");
        this.jRadioButton2.setSelected(false);
        this.jRadioButton1.setSelected(false);
        this.jLabel1.setText((String)null);
    }

    private void jRadioButton1ActionPerformed(ActionEvent evt) {
        if (this.jRadioButton1.isSelected()) {
            this.resetBoard();
            this.jButton1.setText("");
            this.jButton2.setText("");
            this.jButton3.setText("");
            this.jButton4.setText("");
            this.jButton5.setText("");
            this.jButton6.setText("");
            this.jButton7.setText("");
            this.jButton8.setText("");
            this.jButton9.setText("");
            this.jRadioButton2.setSelected(false);
            this.jLabel1.setText((String)null);
            this.player = 0;
            this.helpper = 0;
        }

    }

    private void jRadioButton2ActionPerformed(ActionEvent evt) {
        if (this.jRadioButton2.isSelected()) {
            this.resetBoard();
            this.jButton1.setText("");
            this.jButton2.setText("");
            this.jButton3.setText("");
            this.jButton4.setText("");
            this.jButton5.setText("");
            this.jButton6.setText("");
            this.jButton7.setText("");
            this.jButton8.setText("");
            this.jButton9.setText("");
            this.jRadioButton1.setSelected(false);
            this.player = 1;
            this.helpper = 1;
            this.jLabel1.setText((String)null);
            Point p = new Point((int)(Math.random() * (double)3.0F), (int)(Math.random() * (double)3.0F));
            if (p.x == 0 && p.y == 0) {
                this.jButton1.setText("X");
            } else if (p.x == 0 && p.y == 1) {
                this.jButton2.setText("X");
            } else if (p.x == 0 && p.y == 2) {
                this.jButton3.setText("X");
            } else if (p.x == 1 && p.y == 0) {
                this.jButton4.setText("X");
            } else if (p.x == 1 && p.y == 1) {
                this.jButton5.setText("X");
            } else if (p.x == 1 && p.y == 2) {
                this.jButton6.setText("X");
            } else if (p.x == 2 && p.y == 0) {
                this.jButton7.setText("X");
            } else if (p.x == 2 && p.y == 1) {
                this.jButton8.setText("X");
            } else if (p.x == 2 && p.y == 2) {
                this.jButton9.setText("X");
            }

            this.board[p.x][p.y] = 0;
        }

    }

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(new 13());
    }
}
*/
