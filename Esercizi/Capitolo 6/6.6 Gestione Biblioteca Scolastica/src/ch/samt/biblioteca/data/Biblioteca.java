package ch.samt.biblioteca.data;

import ch.samt.biblioteca.model.ItemBiblioteca;
import ch.samt.biblioteca.model.Libro;

import java.util.*;

public class Biblioteca {
    private ArrayList<ItemBiblioteca> catalogo;
    private Set<String> codiciUsati;
    private Map<String, ArrayList<ItemBiblioteca>> elementiPerAutore;
    private Queue<ItemBiblioteca> prenotazioniFIFO;
    private Stack<ItemBiblioteca> consegneUrgentiLIFO;

    //COSTRUTTORE

    public Biblioteca() {
        this.catalogo = new ArrayList<ItemBiblioteca>();
        this.codiciUsati = new HashSet<String>();
        this.elementiPerAutore = new HashMap<String, ArrayList<ItemBiblioteca>>();
        this.prenotazioniFIFO = new PriorityQueue<ItemBiblioteca>();
        this.consegneUrgentiLIFO = new Stack<ItemBiblioteca>();
    }

    //GETTER
    public ArrayList<ItemBiblioteca> getCatalogo() {
        return catalogo;
    }

    public Map<String, ArrayList<ItemBiblioteca>> getElementiPerAutore() {
        return elementiPerAutore;
    }

    //METODI
    public boolean aggiungiItem(ItemBiblioteca item) {
        boolean aggiunto = false;
        for (ItemBiblioteca i : catalogo) {
            if (i.equals(item)) {
                return aggiunto;
            }
        }

        if (item instanceof Libro) {
            this.catalogo.add(item);
            aggiunto = true;
            return aggiunto;
        }
        return aggiunto;
    }

    public ArrayList<ItemBiblioteca> getElementiDiAutore(String autore) {
        return elementiPerAutore.get(autore);
    }

    public void aggiungiPrenotazioneFIFO(ItemBiblioteca item) {
        this.prenotazioniFIFO.add(item);
    }

    public ItemBiblioteca prossimaPrenotazioneFIFO() {
        return this.prenotazioniFIFO.peek();
    }

    public void aggiungiConsegnaUrgenteLIFO(ItemBiblioteca item) {
        consegneUrgentiLIFO.add(item);
    }

    public ItemBiblioteca prossimaConsegnaLIFO(){
        return consegneUrgentiLIFO.getLast();
    }
}
