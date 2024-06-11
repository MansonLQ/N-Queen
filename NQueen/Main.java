package NQueen;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int[] state = NQueen.generateRandomState();
        int[][] board = NQueen.createBoard(state);

        NQueen.displayState(state);
        NQueen.displayBoard(board);

        int total = NQueen.getConflictingQueensCount(board, state);
        System.out.println("Conflicts: " + total);
        System.out.println();
        int conflicts = HillClimbing.steepestAscentHillClimbing(board, state);

        // hillClimbingAverage();

    }

    private static void hillClimbingAverage() {
        ArrayList<Integer> remainingConflicts = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int[] state = NQueen.generateRandomState();
            int[][] board = NQueen.createBoard(state);

            int conflicts = HillClimbing.steepestAscentHillClimbing(board, state);
            remainingConflicts.add(conflicts);
        }

        // System.out.println(remainingConflicts.toString());

        Double sum = 0.0;
        for (int remainingConflict : remainingConflicts) {
            if (remainingConflict == 0) {
                sum++;
            }
        }
        Double average = sum / remainingConflicts.size();
        System.out.println(average);

    }

}