import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ComicInventory {
    private ArrayList<Comic> collection;

    public ComicInventory(ArrayList<Comic> collection) {
        this.collection = new ArrayList<>();
    }

    public void exportToCSV(String path) {
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
    }
}
