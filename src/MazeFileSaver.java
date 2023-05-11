import java.io.*;

public class MazeFileSaver {
    private File file;

    public MazeFileSaver(File file) {
        this.file = file;
    }

    public void writeToFile(String mazeGrid) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(mazeGrid);
        } catch (
        FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
