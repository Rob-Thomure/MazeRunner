public class FrontierCell {
    Coordinates frontierCellCoordinates;
    Coordinates connectingCellCoordinates;

    public FrontierCell(Coordinates frontierCellCoordinates, Coordinates connectingCellCoordinates) {
        this.frontierCellCoordinates = frontierCellCoordinates;
        this.connectingCellCoordinates = connectingCellCoordinates;
    }

    public Coordinates getFrontierCellCoordinates() {
        return frontierCellCoordinates;
    }

    public Coordinates getConnectingCellCoordinates() {
        return connectingCellCoordinates;
    }
}
