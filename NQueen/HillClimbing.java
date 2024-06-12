package NQueen;

import java.util.Arrays;

public class HillClimbing {
    private final static int N = NQueen.N;

    public static int[] getBestNeighbor(int[][] board, int[] state) {
        int[] bestState = Arrays.copyOf(state, N);

        // initial best conflicting queens count
        int bestConflicts = NQueen.getConflictingQueensCount(board, state);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != state[i]) { // not a queen tile
                    int[] neighborState = Arrays.copyOf(state, N);
                    neighborState[i] = j; // move queen to current

                    int[][] neighborBoard = NQueen.createBoard(neighborState);
                    int neighborConflicts = NQueen.getConflictingQueensCount(neighborBoard, neighborState);

                    if (neighborConflicts < bestConflicts) {
                        bestConflicts = neighborConflicts;
                        bestState = Arrays.copyOf(neighborState, N);
                    }
                }
            }
        }

        return bestState;
    }

    public static int[] steepestAscentHillClimbing(int[][] board, int[] state) {
        int[] result = new int[2];

        int searchCost = 0;
        boolean localMaximum = false;
        int currentConflicts = -1;

        while (!localMaximum) {
            currentConflicts = NQueen.getConflictingQueensCount(board, state);
            int[] neighborState = Arrays.copyOf(state, N);

            int[] bestNeighborState = getBestNeighbor(board, neighborState);
            int[][] bestNeighborBoard = NQueen.createBoard(bestNeighborState);

            // conflict count of the new neighbor
            int bestNeighborConflicts = NQueen.getConflictingQueensCount(bestNeighborBoard, bestNeighborState);

            if (bestNeighborConflicts < currentConflicts) {
                state = Arrays.copyOf(bestNeighborState, N);
                board = NQueen.createBoard(state);

                searchCost++;

                // System.out.println("Current state:");
                // displayState(state);
                // displayBoard(board);
                // System.out.println("Conflicts: " + bestNeighborConflicts);
            } else {
                localMaximum = true;
                System.out.println("Local maximum reached:");
                NQueen.displayState(state);
                NQueen.displayBoard(board);
                System.out.println("Conflicts: " + currentConflicts);

                result[0] = currentConflicts;
                result[1] = searchCost;
                return result;
            }
        }

        result[0] = currentConflicts;
        result[1] = searchCost;
        return result; // found the global maximum
    }
}
