package NQueen;

import java.util.ArrayList;

public class Queen {
    private static int queenCount = 0;
    private int uniqueNumber;
    private int x;
    private int y;

    public Queen(int x, int y) {
        queenCount++;
        this.uniqueNumber = queenCount;
        this.x = x;
        this.y = y;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        // String queen = "(" + x + ", " + y + ") = " + uniqueNumber;
        String queen = Integer.toString(uniqueNumber);
        return queen;
    }

    public ArrayList<Queen> getDiagonalConflicts(Queen[][] board) {
        ArrayList<Queen> values = new ArrayList<>();

        int row = x;
        int column = y - 1;
        while (column >= 0) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            column--;
        }

        row = x;
        column = y + 1;
        while (column < board[0].length) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            column++;
        }

        row = x - 1;
        column = y - 1;
        while (row >= 0 && column >= 0) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            row--;
            column--;
        }

        row = x + 1;
        column = y + 1;
        while (row < board.length && column < board[0].length) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            row++;
            column++;
        }

        row = x - 1;
        column = y + 1;
        while (row >= 0 && column < board[0].length) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            row--;
            column++;
        }

        row = x + 1;
        column = y - 1;
        while (row < board.length && column >= 0) {
            Queen conflictingQueen = board[row][column];

            if (conflictingQueen != null) {
                values.add(conflictingQueen);
            }

            row++;
            column--;
        }

        return values;
    }
}
