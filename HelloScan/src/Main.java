//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //creato oggetto scanner

        System.out.print("Inserisci il tuo nome: ");
        String nome = input.nextLine(); //letto input da tastiera e salvato nella variabile nome
        System.out.print("Inserisci il tuo cognome: ");
        String cognome = input.nextLine();
        System.out.print("Inserisci la tua età: ");
        int eta;
        try {
            eta = input.nextInt();
        } catch (java.util.InputMismatchException nfe) {
            System.out.print("L'età è da inserire in un formato numerico intero");
            return;
        }


        System.out.println("Ciao "+nome+" "+cognome+". La tua età: "+eta);
    }
}