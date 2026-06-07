//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] stud = {"Carlo", "Mauro", "Pietro"};
        Registro reg1 = new Registro(stud, 2);
        reg1.inserisciVoti();
        System.out.println("\n");
        reg1.stampaVoti();
    }
}