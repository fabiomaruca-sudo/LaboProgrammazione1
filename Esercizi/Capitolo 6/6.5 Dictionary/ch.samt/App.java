import dictionary.Dictionary;
import dictionary.Entry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.aggiungi(new Entry("cane", "dog"));
        dictionary.aggiungi(new Entry("gatto", "cat"));
        dictionary.aggiungi(new Entry("umano", "human"));

        System.out.println(dictionary.cerca("gatto"));
    }
}