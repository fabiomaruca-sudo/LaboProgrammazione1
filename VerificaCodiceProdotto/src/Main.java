/**
 * Esercizio Esempio in classe
 * Programma che permette verificare dei codici di prodotti
 *
 * @author Fabio Maruca
 * @version 05.10.2025
 */
public class Main {
    public static void main(String[] args) {
        for (String codice : args) {
            if (!codice.startsWith("PROD-")) {
                System.out.println("Codice non valido: " + codice);
                System.out.println("Motivo: Il codice deve iniziare con 'PROD-'.");
            } else {
                String parteNumerica = codice.substring(5);
                int count = 0;
                boolean soloNumeri = true;
                for (int i = 0; i < parteNumerica.length(); i++) {
                    char c = parteNumerica.charAt(i);
                    if (Character.isDigit(c)) count++;
                    else soloNumeri = false;
                }
                if (soloNumeri && count >= 4) {
                    System.out.println("Codice valido: " + codice);
                } else {
                    System.out.println("Codice non valido: " + codice);
                    System.out.println("Motivo: Il codice deve contenere almeno 4 cifre numeriche dopo 'PROD-'.");
                }
            }
        }
    }
}
