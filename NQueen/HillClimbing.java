package NQueen;

import java.util.Arrays;

public class HillClimbing {
    private final static int N = NQueen.N;

    public static int[] getBestNeighbor(int[][] board, int[] state) { // find the lowest conflicting value neighbor
        int[] bestState = Arrays.copyOf(state, N); // initially, set the best state to the initial state

        // initial best conflicting queens count
        int bestConflicts = NQueen.getConflictingQueensCount(board, state);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { // iterate through all the tiles on a board
                if (j != state[i]) { // if the current tile is not a queen tile
                    int[] neighborState = Arrays.copyOf(state, N); // create a neighbor
                    neighborState[i] = j; // change the queens row to a new row from 0-7

                    int[][] neighborBoard = NQueen.createBoard(neighborState); // create corresponding board
                    int neighborConflicts = NQueen.getConflictingQueensCount(neighborBoard, neighborState);
                    // get the conflict count

                    if (neighborConflicts < bestConflicts) { // compare count to best state
                        bestConflicts = neighborConflicts; // if the count is lower, update the best count
                        bestState = Arrays.copyOf(neighborState, N); // update the best state
                    }
                }
            }
        }

        return bestState; // return should be the lowest count out of all 7*8=56 possible neighbors
    }

    public static int[] steepestAscentHillClimbing(int[][] board, int[] state) { // hillclimbing algorithm
        int[] result = new int[2];

        int searchCost = 0; // search cost is represented by each neighbor found with a lower conflict count
        boolean localMaximum = false; // algorithm will stop when there are no next better states
        int currentConflicts = -1;

        while (!localMaximum) {
            currentConflicts = NQueen.getConflictingQueensCount(board, state); // current conflict count

            int[] bestNeighborState = getBestNeighbor(board, state); // get state's best neighbor
            int[][] bestNeighborBoard = NQueen.createBoard(bestNeighborState); // get board of best nieghbor

            // conflict count of the best neighbor
            int bestNeighborConflicts = NQueen.getConflictingQueensCount(bestNeighborBoard, bestNeighborState);

            if (bestNeighborConflicts < currentConflicts) { // if the best neighbor has a lower conflict count
                state = Arrays.copyOf(bestNeighborState, N); // update the best state
                board = NQueen.createBoard(state); // create a board for the best state

                searchCost++; // increase search cost for each better state found

                // System.out.println("Current state:");
                // displayState(state);
                // displayBoard(board);
                // System.out.println("Conflicts: " + bestNeighborConflicts);
            } else {
                localMaximum = true; // if no better state is found, reached local maxima
                System.out.println("Local maximum reached:");
                NQueen.displayState(state);
                NQueen.displayBoard(board); // display local maxima
                System.out.println("Conflicts: " + currentConflicts);

                result[0] = currentConflicts;
                result[1] = searchCost;
                return result; // return conflict count and search cost
            }
        }

        return result; // empty array
    }
}
