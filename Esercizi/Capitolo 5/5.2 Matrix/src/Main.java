//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Matrice mat1 = new Matrice(5, 5);
        mat1.StampaMatrice();
        mat1.PopolaMatrice();
        System.out.println("\n");
        mat1.StampaMatrice();
        mat1.SetCella(3,4, 200);
        System.out.println();
        System.out.println(mat1.GetCella(3,4));
    }
}