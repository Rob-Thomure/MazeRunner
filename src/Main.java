import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the size of a maze");
        Scanner scanner = new Scanner(System.in);
        int numRows = scanner.nextInt();
        int numColumns = scanner.nextInt();
        RandomMazeGenerator randomMazeGenerator = new RandomMazeGenerator(numRows, numColumns);
        Cell[][] maze = randomMazeGenerator.getMazeGrid();
        String mazeString = new MazeFormatter().formatMaze(maze);
        System.out.println(mazeString);
    }
}