import java.util.List;
import java.util.Random;

public class RandomMazeGenerator {
    private final int numRows;
    private final int numColumns;
    private final Cell[][] grid;
    private final FrontierCells frontierCells;

    public RandomMazeGenerator(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.grid = new Cell[numRows][numColumns];
        this.frontierCells = new FrontierCells();
    }

    private void generateMaze() {
        fillGridWithBlockedCells();
        Coordinates startingCell = setRandomStartingCell();
        List<FrontierCell> newFrontierCells = new FrontierCellLocator(grid, startingCell).findNewFrontierCells();
        frontierCells.addFrontierCells(newFrontierCells);
        while (frontierCells.hasFrontierCells()) {
            FrontierCell randomFrontierCell = frontierCells.getRandomFrontierCell();
            setFrontierCellAndConnectingCellToPassage(randomFrontierCell);
            newFrontierCells = new FrontierCellLocator(grid, randomFrontierCell.getFrontierCellCoordinates())
                    .findNewFrontierCells();
            frontierCells.addFrontierCells(newFrontierCells);
            frontierCells.removeFrontierCell(randomFrontierCell);
        }
    }

    public Cell[][] getMazeGrid() {
        return null;
    }

    private void fillGridWithBlockedCells() {
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                grid[row][column] = new Cell();
            }
        }
    }

    private Coordinates setRandomStartingCell() {
        int randomNum = 0;
        Random random = new Random();
        // prevents top border wall or bottom border wall from being used as a starting location
        while (randomNum == 0 || randomNum == numRows - 1) {
            randomNum = random.nextInt(numRows);
        }
        this.grid[randomNum][0].setStatePassage();
        return new Coordinates(0, randomNum);
    }

    private void setFrontierCellAndConnectingCellToPassage(FrontierCell frontierCell) {
        Coordinates frontierCellCoords = frontierCell.getFrontierCellCoordinates();
        Coordinates connectingCellCoords = frontierCell.getConnectingCellCoordinates();
        grid[frontierCellCoords.getRow()][frontierCellCoords.getColumn()].setStatePassage();
        grid[connectingCellCoords.getRow()][frontierCellCoords.getColumn()].setStatePassage();
    }

}
