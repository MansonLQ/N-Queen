package NQueen;

public class Main {
    public static void main(String[] args) throws Exception {
        Board queensBoard = new Board();
        queensBoard.randomizeBoard();
        queensBoard.displayBoard();
        System.out.println();
        queensBoard.calculateTotalConflictPairs();

    }

    public static void steepestAscentHillClimbing() {

    }
}
