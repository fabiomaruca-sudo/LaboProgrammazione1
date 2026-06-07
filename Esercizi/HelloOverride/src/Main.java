//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Persona ps1 = new Persona("Carlo", 20, "AIO-20");
        Persona ps2 = new Persona("Carlo", 20, "AIO-20");

        System.out.println(ps1.equals(ps2));

    }
}