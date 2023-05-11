import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeFileLoader {
    private File file;

    public MazeFileLoader(File file) {
        this.file = file;
    }

    public String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            return stringBuilder.toString();
        }
        return stringBuilder.toString();
    }
}
