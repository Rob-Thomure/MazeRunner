import java.io.File;
import java.util.Scanner;

public class MazeRunnerMenu {
    private Cell[][] mazeGrid;
    private boolean exit;

    public MazeRunnerMenu() {
        this.mazeGrid = new Cell[0][0];
        this.exit = false;
    }

    public void run() {
        while (!exit) {
            int option = printMenu();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch (option) {
            case 0:
                this.exit = true;
                System.out.println("Bye!");
                break;
            case 1:
                generateNewMaze();
                printMaze();
                break;
            case 2:
                loadMaze();
                break;
            case 3:
                saveMaze();
                break;
            case 4:
                printMaze();
                break;
        }
    }

    private void saveMaze() {
        MazeFileFormatter mazeFileFormatter = new MazeFileFormatter();
        String mazeText = mazeFileFormatter.formatToText(this.mazeGrid);
        File file = new File(new Scanner(System.in).nextLine());
        System.out.println();
        MazeFileSaver mazeFileSaver = new MazeFileSaver(file);
        mazeFileSaver.writeToFile(mazeText);
    }

    private void loadMaze() {
        File file = new File(new Scanner(System.in).nextLine());
        MazeFileLoader mazeFileLoader = new MazeFileLoader(file);
        String mazeTextFromFile = mazeFileLoader.readFromFile();
        if (mazeTextFromFile.isEmpty()) {
            System.out.println("The file " + file + " does not exist");
        } else {
            if (mazeTextFromFile.contains("BLOCKED") && mazeTextFromFile.contains("PASSAGE")) {
                MazeFileFormatter mazeFileFormatter = new MazeFileFormatter();
                this.mazeGrid = mazeFileFormatter.formatToGrid(mazeTextFromFile);
            } else {
                System.out.println("Cannot load the maze. It has an invalid format");
            }
        }
        System.out.println();
    }

    private void printMaze() {
        System.out.println();
        MazeFormatter mazeFormatter = new MazeFormatter();
        System.out.println(mazeFormatter.formatMaze(this.mazeGrid));
    }

    private void generateNewMaze() {
        System.out.println("Enter the size of a new maze");
        int mazeSize = new Scanner(System.in).nextInt();
        RandomMazeGenerator randomMazeGenerator = new RandomMazeGenerator(mazeSize, mazeSize);
        this.mazeGrid = randomMazeGenerator.getMazeGrid();
    }

    private int printMenu() {
        if (this.mazeGrid.length == 0) {
            return printNoLoadedMazeMenu();
        } else {
            return printLoadedMazeMenu();
        }
    }

    private int printNoLoadedMazeMenu() {
        String input = "";
        boolean validOption = false;
        while (!validOption) {
            System.out.println("=== Menu ===\n" +
                    "1. Generate a new maze\n" +
                    "2. Load a maze\n" +
                    "0. Exit");
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
            if (input.matches("[0-2]")) {
                validOption = true;
            } else {
                System.out.println("Incorrect option. Please try again.");
            }
        }
        return Integer.parseInt(input);
    }

    private int printLoadedMazeMenu() {
        String input = "";
        boolean validOption = false;
        while (!validOption) {
            System.out.println("=== Menu ===\n" +
                    "1. Generate a new maze\n" +
                    "2. Load a maze\n" +
                    "3. Save the maze\n" +
                    "4. Display the maze\n" +
                    "0. Exit");
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
            if (input.matches("[0-4]")) {
                validOption = true;
            } else {
                System.out.println("Incorrect option. Please try again.");
            }
        }
        return Integer.parseInt(input);
    }
}
