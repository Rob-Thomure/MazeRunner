public class MazeSolver {
    Cell[][] grid;
    Cell[][] solution;
    int gridSize;

    public MazeSolver(Cell[][] grid) {
        this.grid = grid;
        this.gridSize = grid.length;
        this.solution = copyGrid(grid);
    }

    public Cell[][] getSolvedMaze() {
        solveMaze(findStart(), findEnd());
        return solution;
    }

    private Cell[][] copyGrid(Cell[][] grid) {
        Cell[][] newGrid = new Cell[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Cell cell = grid[i][j];
                Cell newCell = new Cell();
                if (cell.getState() == LayoutState.PASSAGE) {
                    newCell.setStatePassage();
                }
                newGrid[i][j] = newCell;
            }
        }
        return newGrid;
    }

    private boolean solveMaze(Coordinates start, Coordinates end) {
        // if end is reached return (start is represented as the current coordinates)
        if (start.equals(end)) {
            solution[end.getRow()][end.getColumn()].setStateTraveled();
            return true;
        }
        if (start.getRow() >= 0 && start.getColumn() >=0
                && start.getRow() < gridSize && start.getColumn() < gridSize
                && solution[start.getRow()][start.getColumn()].getState() == LayoutState.PASSAGE
                && grid[start.getRow()][start.getColumn()].getState() == LayoutState.PASSAGE) {
            solution[start.getRow()][start.getColumn()].setStateTraveled();

            if (solveMaze(new Coordinates(start.getRow() + 1, start.getColumn()), end)) {
                return true;
            }
            if (solveMaze(new Coordinates(start.getRow(), start.getColumn() + 1), end)) {
                return true;
            }
            if (solveMaze(new Coordinates(start.getRow() - 1, start.getColumn()), end)) {
                return true;
            }
            if (solveMaze(new Coordinates(start.getRow(), start.getColumn() - 1), end)) {
                return true;
            }
            // backTrack
            solution[start.getRow()][start.getColumn()] = new Cell();
            solution[start.getRow()][start.getColumn()].setStatePassage();
            return false;
        }
        return false;
    }

    private Coordinates findStart() {
        int row = -1;
        for (int i = 0; i < gridSize; i++) {
            if (grid[i][0].getState() == LayoutState.PASSAGE) {
                row = i;
            }
        }
        return new Coordinates(row, 0);
    }

    private Coordinates findEnd() {
        int row = -1;
        for (int i = 0; i < gridSize; i++) {
            if (grid[i][gridSize - 1].getState() == LayoutState.PASSAGE) {
                row = i;
            }
        }
        return new Coordinates(row, gridSize - 1);
    }
}
