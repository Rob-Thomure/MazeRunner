
public class MazeFormatter {

    public String formatMaze(Cell[][] grid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var row : grid) {
            for (var column : row) {
                if (column.getState() == LayoutState.BLOCKED) {
                    stringBuilder.append("\u2588\u2588");
                } else if (column.getState() == LayoutState.TRAVELED) {
                    stringBuilder.append("//");
                } else {
                    stringBuilder.append("  ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
