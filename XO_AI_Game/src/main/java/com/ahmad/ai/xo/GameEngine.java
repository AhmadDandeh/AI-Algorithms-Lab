package com.ahmad.ai.xo;

import java.util.ArrayList;

public class GameEngine {
    public int bestX, bestY;

    public int alphaBeta(int[][] board, int alpha, int beta, int turn, int currentPlayer, int depth, int helper1) {
        if (isGameOver(board)) {
            return evaluateBoard(board, helper1);
        }

        ArrayList<Point> availablePoints = getAvailableStates(board);

        if (turn == 0) { // Maximizing Player
            for (Point p : availablePoints) {
                int score = alphaBeta(placeMove(board, p, currentPlayer), alpha, beta, 1, (currentPlayer + 1) % 2, depth + 1, helper1);
                if (score > alpha) {
                    alpha = score;
                    if (depth == 0) {
                        this.bestX = p.x;
                        this.bestY = p.y;
                    }
                }
                if (alpha >= beta) break;
            }
            return alpha;
        } else { // Minimizing Player
            for (Point p : availablePoints) {
                int score = alphaBeta(placeMove(board, p, currentPlayer), alpha, beta, 0, (currentPlayer + 1) % 2, depth + 1, helper1);
                if (score < beta) {
                    beta = score;
                }
                if (alpha >= beta) break;
            }
            return beta;
        }
    }

    private int evaluateBoard(int[][] board, int helper1) {
        if (hasXWon(board)) return (helper1 == 1) ? 1 : -1;
        if (hasOWon(board)) return (helper1 == 1) ? -1 : 1;
        return 0;
    }

    public int[][] placeMove(int[][] oldBoard, Point p, int player) {
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) System.arraycopy(oldBoard[i], 0, newBoard[i], 0, 3);
        newBoard[p.x][p.y] = player;
        return newBoard;
    }

    public ArrayList<Point> getAvailableStates(int[][] b) {
        ArrayList<Point> available = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == -1) available.add(new Point(i, j));
            }
        }
        return available;
    }

    public boolean isGameOver(int[][] b) {
        return hasXWon(b) || hasOWon(b) || getAvailableStates(b).isEmpty();
    }

    public boolean hasXWon(int[][] b) { return checkWin(b, 0); }
    public boolean hasOWon(int[][] b) { return checkWin(b, 1); }

    private boolean checkWin(int[][] b, int p) {
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p) return true;
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p) return true;
        }
        return (b[0][0] == p && b[1][1] == p && b[2][2] == p) ||
                (b[0][2] == p && b[1][1] == p && b[2][0] == p);
    }
}
/**
import java.util.ArrayList;

public class GameEngine {
    int bestX;
    int bestY;

    public int alpha_beta_fun(int[][] a, int alpha, int beta, int turn, int player, int x, int helper1) {
        if (this.isGameOver(a)) {
            if (this.hasXWon(a) && helper1 == 1) {
                return 1;
            } else if (this.hasXWon(a) && helper1 == 0) {
                return -1;
            } else if (this.hasOWon(a) && helper1 == 1) {
                return -1;
            } else {
                return this.hasOWon(a) && helper1 == 0 ? 1 : 0;
            }
        } else {
            ArrayList<Point> pointsAvailable = this.getAvailableStates(a);
            if (turn == 0) {
                for(int i = 0; i < pointsAvailable.size(); ++i) {
                    int score = this.alpha_beta_fun(this.placeAMove(a, (Point)pointsAvailable.get(i), player), alpha, beta, 1, (player + 1) % 2, x + 1, helper1);
                    if (score > alpha && x == 0) {
                        this.bestX = ((Point)pointsAvailable.get(i)).x;
                        this.bestY = ((Point)pointsAvailable.get(i)).y;
                        alpha = score;
                    }

                    if (score > alpha) {
                        alpha = score;
                    }

                    if (alpha >= beta) {
                        return alpha;
                    }
                }

                return alpha;
            } else if (turn == 1) {
                for(int i = 0; i < pointsAvailable.size(); ++i) {
                    int score = this.alpha_beta_fun(this.placeAMove(a, (Point)pointsAvailable.get(i), player), alpha, beta, 0, (player + 1) % 2, x + 1, helper1);
                    if (score < beta) {
                        beta = score;
                    }

                    if (alpha >= beta) {
                        return beta;
                    }
                }

                return beta;
            } else {
                return -30;
            }
        }
    }

    public int[][] placeAMove(int[][] aa, Point point, int player) {
        int[][] w = new int[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                w[i][j] = aa[i][j];
            }
        }

        w[point.x][point.y] = player;
        return w;
    }

    public ArrayList<Point> getAvailableStates(int[][] b) {
        ArrayList<Point> availablePoints = new ArrayList();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (b[i][j] == -1) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }

        return availablePoints;
    }

    public boolean isGameOver(int[][] b) {
        return this.hasXWon(b) || this.hasOWon(b) || this.getAvailableStates(b).isEmpty();
    }

    public boolean hasXWon(int[][] board) {
        if ((board[0][0] != board[1][1] || board[0][0] != board[2][2] || board[0][0] != 0) && (board[0][2] != board[1][1] || board[0][2] != board[2][0] || board[0][2] != 0)) {
            for(int i = 0; i < 3; ++i) {
                if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 0 || board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 0) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public boolean hasOWon(int[][] board) {
        if ((board[0][0] != board[1][1] || board[0][0] != board[2][2] || board[0][0] != 1) && (board[0][2] != board[1][1] || board[0][2] != board[2][0] || board[0][2] != 1)) {
            for(int i = 0; i < 3; ++i) {
                if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1 || board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
*/
