import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Carrello c1 = new Carrello("spesa");
        Alimento pasta = new Alimento("tagliatelle", "carboidrati", "11-09-2006", 4);

        c1.addAlimento(pasta);
    }
}