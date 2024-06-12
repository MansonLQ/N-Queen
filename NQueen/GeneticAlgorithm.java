package NQueen;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    private final static int N = NQueen.N; // number of queens on the board
    private final static int POPULATION_SIZE = 100; // number of boards per population
    private final static int PARENT_POOL = 5; // number of boards selected to possibly be parent
    private final static double MUTATION_RATE = 0.05; // percent of random mutations

    public static ArrayList<int[]> initializePopulation() { // create population of random boards
        ArrayList<int[]> population = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            int[] person = new int[N];
            for (int j = 0; j < N; j++) { // for every person in population
                person[j] = random.nextInt(N); // queen rows are random int 0-8
            }
            population.add(person);
        }

        return population; // return population size of POPULATION_SIZE
    }

    public static int getFitness(int[] state) { // fitness score based on conflicting queens count
        int[][] board = NQueen.createBoard(state);
        int conflicts = NQueen.getConflictingQueensCount(board, state);
        return 28 - conflicts; // max number of conflicting queens = 28
        // 28-conflicts is the number of queens that are not in conflict
    }

    public static int[] getParentCandidate(ArrayList<int[]> population) { // get one parent
        Random random = new Random();
        ArrayList<int[]> parentPool = new ArrayList<>();

        for (int i = 0; i < PARENT_POOL; i++) { // get x random boards in the population
            int randomIndex = random.nextInt(POPULATION_SIZE);
            parentPool.add(population.get(randomIndex)); // add boards to parent pool
        }

        int[] best = parentPool.get(0);
        for (int[] individual : parentPool) { // iterate through parent pool
            if (getFitness(individual) > getFitness(best)) {
                best = individual;
            }
        }

        return best; // return highest fitness board out of the parent pool
    }

    public static int[][] crossover(int[] parent1, int[] parent2) { // create two children
        Random random = new Random();
        int crossoverPoint = random.nextInt(N); // generate random crossover point from 0-7

        int[] child1 = new int[N];
        int[] child2 = new int[N];

        for (int i = 0; i < N; i++) { // for each index, swap values based on crossover point
            if (i < crossoverPoint) {
                child1[i] = parent1[i];
                child2[i] = parent2[i];
            } else { // index is past crossover point
                child1[i] = parent2[i];
                child2[i] = parent1[i];
            }
        }

        return new int[][] { child1, child2 }; // 2 parents create 2 children
    }

    public static void mutate(int[] individual) { // chance to randomly change one queen tile
        Random random = new Random();

        for (int i = 0; i < N; i++) { // iterate through board state
            if (random.nextDouble() < MUTATION_RATE) {
                individual[i] = random.nextInt(N); // change the row of a queen in a child
            }
        }
    }

    public static int[] getBestPerson(ArrayList<int[]> population) {
        int[] best = population.get(0);

        for (int[] person : population) { // iterate through population
            if (getFitness(person) > getFitness(best)) {
                best = person; // store the board with the best fitness
            }
        }

        return best; // return the board with the highest fitness score
    }

    public static ArrayList<int[]> evolvePopulation(ArrayList<int[]> population) { // apply mutations for children
        ArrayList<int[]> newPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE / 2; i++) { // get the whole population to breed
            int[] parent1 = getParentCandidate(population);
            int[] parent2 = getParentCandidate(population); // two parents create two children
            int[][] children = crossover(parent1, parent2);
            mutate(children[0]);
            mutate(children[1]); // children have chances to mutate
            newPopulation.add(children[0]);
            newPopulation.add(children[1]); // new population will be same amount as old population
        }

        return newPopulation;
    }

    public static int geneticAlgorithm(int maxGenerations) { // see if solution is found within max generations
        ArrayList<int[]> population = initializePopulation();
        int generation = 0;

        while (generation < maxGenerations) { // generate up to the max
            population = evolvePopulation(population); // greate new generations

            int[] bestIndividual = getBestPerson(population); // take the best board
            int bestFitness = getFitness(bestIndividual); // get the fitnesscore of the best board

            if (bestFitness == 28) { // 28 is the maximum fitness (no conflicts)
                System.out.println("Solution found in generation " + generation + ":");
                NQueen.displayState(bestIndividual);
                NQueen.displayBoard(NQueen.createBoard(bestIndividual));
                return generation; // solution found
            }

            generation++; // update the generation
        }

        System.out.println("No solution found within the maximum number of generations.");
        return -1; // if no board with fitness score of 28, solution was not found within the max
                   // generations
    }

}
