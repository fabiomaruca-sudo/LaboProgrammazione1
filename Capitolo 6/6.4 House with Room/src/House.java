import java.util.ArrayList;

public class House {
    private ArrayList<Room> stanze;

    //COSTRUTTORI

    public House() {
        this.stanze = new ArrayList<Room>();
    }


    //METODI
    //Questo metodo aggiungerà una stanza col metodo di composizione
    public void aggiungiStanza(String nome,float superficieMq) {
        stanze.add(new Room(nome,superficieMq));
    }

    public void visualizzaStanze() {
        if (!stanze.isEmpty()) {
        for (int i = 0; i<stanze.size();i++) {
                System.out.println("Camera " + i+1 + ": " + stanze.get(i).getNome() + " " + stanze.get(i).getSuperficieMq());
            }
        }
    }
}
