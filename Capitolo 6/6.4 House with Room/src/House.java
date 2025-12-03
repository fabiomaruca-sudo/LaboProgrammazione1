import java.util.ArrayList;

public class House {
    private ArrayList<Room> stanze;

    //COSTRUTTORI

    public House() {
        this.stanze = new ArrayList<Room>();
    }


    //METODI
    //Questo metodo aggiungerà una stanza col metodo di composizione
    public void aggiungiStanza(String nome, double superficieMq) {
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (superficieMq <= 0) {
            throw new IllegalArgumentException("SuperficieMq inferiore o uguale a 0");
        }
        stanze.add(new Room(nome,superficieMq));
    }

    public void visualizzaStanze() {
        if (!stanze.isEmpty()) {
        for (int i = 0; i<stanze.size();i++) {
                System.out.println("Camera " + (i+1) + ": " + stanze.get(i).getNome() + " " + stanze.get(i).getSuperficieMq());
            }
        }else  {
            System.out.println("La casa è vuota.");
        }
    }

    public double getSuperficieTotale() {
        double superficie = 0.0;
        for (Room stanza : stanze) {
            superficie += stanza.getSuperficieMq();
        }
        return superficie;
    }

    public Room trovaStanza(String nome) {
        for (Room stanza : stanze) {
            if (stanza.getNome() == nome) {
                return stanza;
            }
        } return null;
    }

    public void rimuoviStanza(String nome) {
        stanze.remove(trovaStanza(nome));
    }
}
