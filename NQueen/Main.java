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
        HillClimbing.steepestAscentHillClimbing(board, state);
        System.out.println();

        // hillClimbingAverage();

        // geneticAlgorithmAverage();

        // GeneticAlgorithm.geneticAlgorithm(1000);

    }

    private static void hillClimbingAverage() {
        ArrayList<Integer> remainingConflicts = new ArrayList<>();
        ArrayList<Integer> searchCosts = new ArrayList<>();
        ArrayList<Double> runTimes = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            int[] state = NQueen.generateRandomState();
            int[][] board = NQueen.createBoard(state);

            long startTime = System.nanoTime();
            int[] result = HillClimbing.steepestAscentHillClimbing(board, state);
            long endTime = System.nanoTime();

            remainingConflicts.add(result[0]);
            searchCosts.add(result[1]);

            long runTime = endTime - startTime;
            double runTimeInMilliseconds = runTime / 1_000_000.0;
            runTimes.add(runTimeInMilliseconds);
        }

        Double sumSolved = 0.0;
        for (int remainingConflict : remainingConflicts) {
            if (remainingConflict == 0) {
                sumSolved++;
            }
        }
        Double solvedAverage = sumSolved / remainingConflicts.size();
        Double percent = solvedAverage * 100;
        Double percentRounded = Math.round(percent * 100.0) / 100.0;

        Double sumSearchCosts = 0.0;
        for (int searchCost : searchCosts) {
            sumSearchCosts += searchCost;
        }
        Double searchCostAverage = sumSearchCosts / searchCosts.size();
        Double searchCostAverageRounded = Math.round(searchCostAverage * 100.0) / 100.0;

        Double sumRunTimes = 0.0;
        for (Double runTime : runTimes) {
            sumRunTimes += runTime;
        }
        Double runTimeAverage = sumRunTimes / runTimes.size();
        Double roundedRunTimeAverage = Math.round(runTimeAverage * 100.0) / 100.0;

        System.out.println("Average Problems Solved: " + percentRounded + "%");
        System.out.println("Average Search Cost: " + searchCostAverageRounded);
        System.out.println("Average Run Time: " + roundedRunTimeAverage + " ms");

    }

    private static void geneticAlgorithmAverage() {
        ArrayList<Integer> generationResults = new ArrayList<>();
        ArrayList<Double> runTimes = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            long startTime = System.nanoTime();
            int result = GeneticAlgorithm.geneticAlgorithm(500);
            long endTime = System.nanoTime();

            generationResults.add(result);

            long runTime = endTime - startTime;
            double runTimeInMilliseconds = runTime / 1_000_000.0;
            runTimes.add(runTimeInMilliseconds);
        }

        Double sumSolved = 0.0;
        for (int remainingConflict : generationResults) {
            if (remainingConflict != -1) {
                sumSolved++;
            }
        }
        Double solvedAverage = sumSolved / generationResults.size();
        Double percent = solvedAverage * 100;
        Double percentRounded = Math.round(percent * 100.0) / 100.0;

        Double sumSearchCosts = 0.0;
        for (int searchCost : generationResults) {
            if (searchCost != -1) {
                sumSearchCosts += searchCost;
            }
        }
        Double searchCostAverage = sumSearchCosts / sumSolved;
        Double searchCostAverageRounded = Math.round(searchCostAverage * 100.0) / 100.0;

        Double sumRunTimes = 0.0;
        for (Double runTime : runTimes) {
            sumRunTimes += runTime;
        }
        Double runTimeAverage = sumRunTimes / runTimes.size();
        Double roundedRunTimeAverage = Math.round(runTimeAverage * 100.0) / 100.0;

        System.out.println("Average Problems Solved: " + percentRounded + "%");
        System.out.println("Average Search Cost: " + searchCostAverageRounded);
        System.out.println("Average Run Time: " + roundedRunTimeAverage + " ms");
    }

}