//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        int numero = rand.nextInt(100);
        int num_utente = -1;
        int ciclo = 0;

        while (num_utente != numero) {

            try {
                System.out.print("Il tuo tentativo: ");
                num_utente = input.nextInt();
                ciclo--;
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero intero valido");
                num_utente = -1;
                input.nextLine();
            }

            if (num_utente < numero) {
                System.out.println("Troppo basso!");
            }else if (num_utente > numero)  {
                System.out.println("Troppo alto!");
            }

            ciclo++;
        }
        System.out.println("Indovinato! Numero: " + numero + " Tentativi: " + ciclo);
    }
}