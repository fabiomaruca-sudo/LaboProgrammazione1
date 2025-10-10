//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        for (int val = 0; val < args.length; val++) {
            try {
                float number = Float.parseFloat(args[val]);
                verificaSegno(number);

            } catch (NumberFormatException e) {
                System.out.println("Inserire solo numeri");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! " + e.getMessage());
            }
        }
    }

    public static void verificaSegno(float n) {
        if (n < 0) {
            System.out.println("Il numero " + n + " è negativo");
        } else if (n > 0) {
            System.out.println("Il numero " + n + " è positivo");
        } else {
            throw new IllegalArgumentException("Il numero non può essere 0");
        }
    }
}