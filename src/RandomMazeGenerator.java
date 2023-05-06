import java.util.ArrayList;
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
        generateMaze();
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
        setRandomExitCell();
    }

    public Cell[][] getMazeGrid() {
        return grid;
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
        return new Coordinates(randomNum, 0);
    }

    private void setFrontierCellAndConnectingCellToPassage(FrontierCell frontierCell) {
        Coordinates frontierCellCoords = frontierCell.getFrontierCellCoordinates();
        Coordinates connectingCellCoords = frontierCell.getConnectingCellCoordinates();
        grid[frontierCellCoords.getRow()][frontierCellCoords.getColumn()].setStatePassage();
        grid[connectingCellCoords.getRow()][connectingCellCoords.getColumn()].setStatePassage();
    }

    private void setRandomExitCell() {
        Random random = new Random();
        List<Coordinates> coordinatesList;
        coordinatesList = findPossibleExits(2);
        if (coordinatesList.size() > 0) {
            int randomNum = random.nextInt(coordinatesList.size());
            Coordinates coordinates = coordinatesList.get(randomNum);
            grid[coordinates.getRow()][coordinates.getColumn()].setStatePassage();
        } else {
            coordinatesList = findPossibleExits(3);
            int randomNum = random.nextInt(coordinatesList.size());
            Coordinates coordinates = coordinatesList.get(randomNum);
            grid[coordinates.getRow()][coordinates.getColumn()].setStatePassage();
            grid[coordinates.getRow()][coordinates.getColumn() - 1].setStatePassage();
        }
    }

    private List<Coordinates> findPossibleExits(int numFromWall) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][numColumns - numFromWall].getState() == LayoutState.PASSAGE) {
                coordinatesList.add(new Coordinates(i, numColumns - 1));
            }
        }
        return coordinatesList;
    }
}
