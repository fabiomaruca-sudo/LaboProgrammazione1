/**
 * Esercizio Esempio in classe
 * Programma che permette di calcolare il quadrato dei numeri
 *
 * @author Fabio Maruca
 * @version 05.10.2025
 */
public class Main {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int numero = Integer.parseInt(arg);
                int quadrato = numero * numero;
                System.out.println("Il quadrato di " + numero + " è " + quadrato);
            } catch (NumberFormatException e) {
                System.out.println("Argomento non valido: " + arg + " (non è un numero intero)");
            }
        }
    }
}

