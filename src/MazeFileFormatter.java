
public class MazeFileFormatter {

    public String formatToText(Cell[][] mazeGrid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mazeGrid.length; i++) {
            for (int j = 0; j < mazeGrid[i].length; j++) {
                if (j < mazeGrid[i].length - 1) {
                    stringBuilder.append(mazeGrid[i][j].getState().toString()).append(" ");
                } else {
                    stringBuilder.append(mazeGrid[i][j].getState().toString()).append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    public Cell[][] formatToGrid(String mazeFileText) {
        String[] textRows = mazeFileText.split("\n");
        int numRows = textRows.length;
        int numColumns = textRows[0].split(" ").length;
        Cell[][] mazeGrid = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            String[] textRow = textRows[i].split(" ");
            for (int j = 0; j < numColumns; j++) {
                Cell cell = new Cell();
                if (textRow[j].equals("PASSAGE")) {
                    cell.setStatePassage();
                }
                mazeGrid[i][j] = cell;
            }
        }
        return mazeGrid;
    }

}
