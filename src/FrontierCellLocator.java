import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FrontierCellLocator {
    private Cell[][] mazeGrid;
    private final Coordinates newPassageCoordinates;

    public FrontierCellLocator(Cell[][] mazeGrid, Coordinates newPassageCoordinates) {
        this.mazeGrid = mazeGrid;
        this.newPassageCoordinates = newPassageCoordinates;
    }

    public List<FrontierCell> findNewFrontierCells() {
        return getFrontierCellCandidates().stream()
                .filter(isWithinGrid)
                .filter(isBlockedCell)
                .toList();
    }

    private List<FrontierCell> getFrontierCellCandidates() {
        List<FrontierCell> frontierCellCandidates = new ArrayList<>();
        int row = newPassageCoordinates.getRow();
        int column = newPassageCoordinates.getColumn();
        Coordinates firstFrontierCellCoords = new Coordinates(row - 2, column);
        Coordinates firstConnectingCellCoords =new Coordinates(row - 1, column);
        FrontierCell firstFrontierCell = new FrontierCell(firstFrontierCellCoords, firstConnectingCellCoords);

        Coordinates secondFrontierCellCoords = new Coordinates(row, column + 2);
        Coordinates secondConnectingCellCoords = new Coordinates(row, column + 1);
        FrontierCell seconFrontierCell = new FrontierCell(secondFrontierCellCoords, secondConnectingCellCoords);

        Coordinates thirdFrontierCellCoords = new Coordinates(row + 2, column);
        Coordinates thirdConnectingCellCoords = new Coordinates(row + 1, column);
        FrontierCell thirdFrontierCell = new FrontierCell(thirdFrontierCellCoords, thirdConnectingCellCoords);

        Coordinates fourthFrontierCellCoords = new Coordinates(row, column - 2);
        Coordinates fourthConnectingCellCoords = new Coordinates(row, column - 2);
        FrontierCell fourthFrontierCell = new FrontierCell(fourthFrontierCellCoords, fourthConnectingCellCoords);

        frontierCellCandidates.add(firstFrontierCell);
        frontierCellCandidates.add(seconFrontierCell);
        frontierCellCandidates.add(thirdFrontierCell);
        frontierCellCandidates.add(fourthFrontierCell);

        return frontierCellCandidates;
    }

    private final Predicate<FrontierCell> rowIsNotFirstRow =
            frontierCell -> frontierCell.getFrontierCellCoordinates().getRow() != 0;

    private final Predicate<FrontierCell> rowIsNotLastRow =
            frontierCell -> frontierCell.getFrontierCellCoordinates().getRow() != mazeGrid.length - 1;

    private final Predicate<FrontierCell> rowIsNotFirstOrLast =
            frontierCell -> rowIsNotFirstRow.and(rowIsNotLastRow).test(frontierCell);

    private final Predicate<FrontierCell> columnIsNotFirst =
            frontierCell -> frontierCell.getFrontierCellCoordinates().getColumn() != 0;

    private final Predicate<FrontierCell> columnIsNotLast =
            frontierCell -> frontierCell.getFrontierCellCoordinates().getColumn() != mazeGrid[0].length;

    private final Predicate<FrontierCell> columnIsNotFirstOrLast =
            frontierCell -> columnIsNotFirst.and(columnIsNotLast).test(frontierCell);


    private final Predicate<FrontierCell> isWithinGrid =
            frontierCell -> rowIsNotFirstOrLast.and(columnIsNotFirstOrLast).test(frontierCell);

    private final Predicate<FrontierCell> isBlockedCell = frontierCell -> {
        int row = frontierCell.getFrontierCellCoordinates().getRow();
        int column = frontierCell.getFrontierCellCoordinates().getColumn();
        return mazeGrid[row][column].getState() == LayoutState.BLOCKED;
    };






}
