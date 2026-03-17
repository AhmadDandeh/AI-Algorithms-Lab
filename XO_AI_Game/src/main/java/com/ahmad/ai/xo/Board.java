package com.ahmad.ai.xo;

public class Board {
    public int[][] board = new int[3][3];
    public int alpha;
    public int beta;
    public int turn;
    public int player;
    public int mainCir;
    public Point bestP = new Point(0, 0);

    public Board(int[][] a, int alpha, int beta, int turn, int player, int x) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.board[i][j] = a[i][j];
            }
        }

        this.alpha = alpha;
        this.beta = beta;
        this.turn = turn;
        this.player = player;
        this.mainCir = x;
    }

    public void displayBoard() {
        System.out.println();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                System.out.print(this.board[i][j] + " ");
            }

            System.out.println();
        }

    }
}

