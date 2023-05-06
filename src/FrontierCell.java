import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrontierCell that = (FrontierCell) o;
        return Objects.equals(frontierCellCoordinates, that.frontierCellCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frontierCellCoordinates);
    }
}
