import java.util.ArrayList;

public class Carrello {
    private String nome;
    private ArrayList<Alimento> alimenti;


    //COSTRUTTORE

    public Carrello(String nome) {
        this.nome = nome;
        this.alimenti = new ArrayList<Alimento>();
    }

    public void addAlimento(Alimento alimento) {
        this.alimenti.add(alimento);
    }

    public void addAlimento(String nome, String categoria, String data_scadenza,int quantita) {
        alimenti.add(new Alimento(nome, categoria, data_scadenza, quantita));
    }

    public void removeAlimento(Alimento alimento) {
        this.alimenti.remove(alimento);
    }
}
