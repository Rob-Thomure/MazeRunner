import java.util.*;

public class FrontierCells {
    private final Set<FrontierCell> frontierCellSet;

    public FrontierCells() {
        this.frontierCellSet = new HashSet<>();
    }

    public void addFrontierCells(List<FrontierCell> frontierCells) {
        this.frontierCellSet.addAll(frontierCells);
    }

    public void removeFrontierCell(FrontierCell frontierCell) {
        frontierCellSet.remove(frontierCell);
    }

    public FrontierCell getRandomFrontierCell() {
        Random random = new Random();
        List<FrontierCell> frontierCellList = frontierCellSet.stream().toList();
        return frontierCellList.get(random.nextInt(frontierCellSet.size()));
    }

    public boolean hasFrontierCells() {
        return !frontierCellSet.isEmpty();
    }
}
