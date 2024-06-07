package NQueen;

import java.util.ArrayList;

public class Board {
    Queen[][] board;
    int conflictCount;

    public Board() {
        board = new Queen[8][8];
    }

    public void randomizeBoard() {
        board = new Queen[8][8];
        for (int i = 0; i < board.length; i++) {
            int randomRow = (int) (Math.random() * 8);
            board[randomRow][i] = new Queen(randomRow, i);
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[i][j].getUniqueNumber() + " ");
                }
            }
            System.out.println();
        }
    }

    public void calculateTotalConflictPairs() {
        ArrayList<Queen> output = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Queen tile = board[i][j];
                if (tile != null) { // if tile contains a queen
                    output = tile.getDiagonalConflicts(board);
                    System.out.println(tile.getUniqueNumber() + " " + output.toString());
                    System.out.println();
                }
            }
        }

    }

}
