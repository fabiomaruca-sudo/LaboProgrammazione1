package ch.samt.biblioteca.data;

import ch.samt.biblioteca.model.ItemBiblioteca;
import java.util.*;

public class Biblioteca {
    private ArrayList<ItemBiblioteca> catalogo;
    private Set<String> codiciUsati;
    private Map<String, ArrayList<ItemBiblioteca>> elementiPerAutore;

    //COSTRUTTORE

    public Biblioteca() {
        this.catalogo = new ArrayList<ItemBiblioteca>();
        this.codiciUsati = new HashSet<String>();
        this.elementiPerAutore = new HashMap<String, ArrayList<ItemBiblioteca>>();
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

        this.catalogo.add(item);
        return aggiunto;
    }

    public ArrayList<ItemBiblioteca> getElementiDiAutore(String autore) {
        return elementiPerAutore.get(autore);
    }
}
