import java.util.Random;

public class Matrice {
    private int Righe;
    private int Colonne;
    private int[][] matrice;

    //COSTRUTTORI

    //vuoto
    public Matrice() {
        Righe = 5;
        Colonne = 5;
        matrice = new int[getRighe()][getColonne()];
    }

    //implementato
    public Matrice(int righe, int colonne) {
        Righe = righe;
        Colonne = colonne;
        matrice = new int[getRighe()][getColonne()];
    }

    //GETTER E SETTER

    public int getRighe() {
        return Righe;
    }

    public int getColonne() {
        return Colonne;
    }


    //METODI
    public void StampaMatrice() {
        for (int i = 0; i < matrice.length; i++) {
            System.out.println();
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j]);
            }
        }
    }

    public void PopolaMatrice() {
        Random rnd1 = new Random();
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = rnd1.nextInt(0,2);
            }
        }
    }

    public int GetCella(int x, int y) {
        return matrice[x][y];
    }

    public void SetCella(int x, int y, int val) {
        matrice[x][y] = val;
    }
}
