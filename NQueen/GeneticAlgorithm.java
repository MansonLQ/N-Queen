package NQueen;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    private final static int N = NQueen.N;
    private final static int POPULATION_SIZE = 100;
    private final static int PARENT_POOL = 5;
    private final static double MUTATION_RATE = 0.05;

    public static ArrayList<int[]> initializePopulation() {
        ArrayList<int[]> population = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            int[] person = new int[N];
            for (int j = 0; j < N; j++) { // for every person in population
                person[j] = random.nextInt(N); // queen rows are random int 0-8
            }
            population.add(person);
        }

        return population;
    }

    public static int getFitness(int[] state) {
        int[][] board = NQueen.createBoard(state);
        int conflicts = NQueen.getConflictingQueensCount(board, state);
        return 28 - conflicts;
    }

    public static int[] getParentCandidate(ArrayList<int[]> population) {
        Random random = new Random();
        ArrayList<int[]> tournament = new ArrayList<>();

        for (int i = 0; i < PARENT_POOL; i++) {
            int randomIndex = random.nextInt(POPULATION_SIZE);
            tournament.add(population.get(randomIndex));
        }

        int[] best = tournament.get(0);
        for (int[] individual : tournament) {
            if (getFitness(individual) > getFitness(best)) {
                best = individual;
            }
        }

        return best;
    }

    public static int[][] crossover(int[] parent1, int[] parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(N);

        int[] child1 = new int[N];
        int[] child2 = new int[N];

        for (int i = 0; i < N; i++) {
            if (i < crossoverPoint) {
                child1[i] = parent1[i];
                child2[i] = parent2[i];
            } else {
                child1[i] = parent2[i];
                child2[i] = parent1[i];
            }
        }

        return new int[][] { child1, child2 };
    }

    public static void mutate(int[] individual) {
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                individual[i] = random.nextInt(N);
            }
        }
    }

    public static int[] getBestPerson(ArrayList<int[]> population) {
        int[] best = population.get(0);

        for (int[] person : population) {
            if (getFitness(person) > getFitness(best)) {
                best = person;
            }
        }

        return best;
    }

    public static ArrayList<int[]> evolvePopulation(ArrayList<int[]> population) {
        ArrayList<int[]> newPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE / 2; i++) {
            int[] parent1 = getParentCandidate(population);
            int[] parent2 = getParentCandidate(population);
            int[][] children = crossover(parent1, parent2);
            mutate(children[0]);
            mutate(children[1]);
            newPopulation.add(children[0]);
            newPopulation.add(children[1]);
        }

        return newPopulation;
    }

    public static int geneticAlgorithm(int maxGenerations) {
        ArrayList<int[]> population = initializePopulation();
        int generation = 0;

        while (generation < maxGenerations) {
            population = evolvePopulation(population);

            int[] bestIndividual = getBestPerson(population);
            int bestFitness = getFitness(bestIndividual);

            if (bestFitness == 28) { // 28 is the maximum fitness (no conflicts)
                System.out.println("Solution found in generation " + generation + ":");
                NQueen.displayState(bestIndividual);
                NQueen.displayBoard(NQueen.createBoard(bestIndividual));
                return generation;
            }

            generation++;
        }

        System.out.println("No solution found within the maximum number of generations.");
        return -1;
    }

}
