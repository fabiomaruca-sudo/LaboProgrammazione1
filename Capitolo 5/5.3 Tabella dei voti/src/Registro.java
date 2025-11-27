import java.util.Scanner;

public class Registro {
    private String[] studenti;
    private double[][] voti;

    public Registro(String[] studenti, int materie) {
        voti = new double[studenti.length][materie];
        this.studenti = studenti;
    }

    public void inserisciVoti() {
        Scanner obj = new Scanner(System.in);
        float voto;
        for (int i = 0; i < voti.length; i++) {
            System.out.println();
            System.out.println("Nome dello studente "+(i+1)+": "+studenti[i]);
            for (int j = 0; j < voti[i].length; j++) {
                System.out.print("Inserisci voto "+(j+1)+": ");
                voto = obj.nextFloat();
                voti[i][j] = voto;
            }
        }
    }

    public void stampaVoti() {
        System.out.print("=== Tabella Voti ===");
        for (int i = 0; i < voti.length; i++) {
            System.out.println();
            System.out.print(studenti[i]+": ");
            for (int j = 0; j < voti[i].length; j++) {
                System.out.print(voti[i][j]+" ");
            }
        }
    }

}
