import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrontierCells {
    private final List<FrontierCell> frontierCellList;

    public FrontierCells() {
        this.frontierCellList = new ArrayList<>();
    }

    public void addFrontierCells(List<FrontierCell> frontierCells) {
        this.frontierCellList.addAll(frontierCells);
    }

    public void removeFrontierCell(FrontierCell frontierCell) {
        frontierCellList.remove(frontierCell);
    }

    public FrontierCell getRandomFrontierCell() {
        Random random = new Random();
        return frontierCellList.get(random.nextInt(frontierCellList.size()));
    }

    public boolean hasFrontierCells() {
        return !frontierCellList.isEmpty();
    }
}
