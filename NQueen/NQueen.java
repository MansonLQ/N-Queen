package NQueen;

import java.util.Arrays;
import java.util.Random;

public class NQueen {
    public final static int N = 8;

    public static int[] generateRandomState() {
        int[] state = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            state[i] = random.nextInt(N);
        }

        return state;
    }

    public static int[][] createBoard(int[] state) {
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[state[i]][i] = 1;
        }

        return board;
    }

    public static void displayBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" 1 ");
                }
            }
            System.out.println();
        }
    }

    public static void displayState(int[] state) {
        System.out.println(Arrays.toString(state));
    }

    public static int getConflictingQueensCount(int[][] board, int[] state) {
        int conflicts = 0;

        for (int i = 0; i < N; i++) {
            int row;
            int column;

            // check if any queens to the left
            row = state[i];
            column = i - 1;
            while (column >= 0) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column--;
            }

            row = state[i];
            column = i + 1;
            // check if any queens to right
            while (column < N) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column++;
            }

            row = state[i] - 1;
            column = i - 1;
            // check if any queens in upper left diagonal
            while (column >= 0 && row >= 0) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column--;
                row--;
            }

            row = state[i] + 1;
            column = i + 1;
            // check if any queens in bottom right diagonal
            while (column < N && row < N) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column++;
                row++;
            }

            row = state[i] - 1;
            column = i + 1;
            // check if any queens in upper right diagonal
            while (column < N && row >= 0) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column++;
                row--;
            }

            row = state[i] + 1;
            column = i - 1;
            // check if any queens in bottom left diagonal
            while (column >= 0 && row < N) {
                if (board[row][column] == 1) {
                    conflicts++;
                }
                column--;
                row++;
            }
        }

        int uniqueConflictPairs = conflicts / 2;
        return uniqueConflictPairs;

    }
}
