package ch.samt.biblioteca.model;

public class Libro extends ItemBiblioteca{
    private String autore;
    private int numeroPagine;

    //COSTRUTTORE
    public Libro(String codice, String scaffale, int annoPubblicazione, String titolo, String autore, int numeroPagine) {
        super(codice, scaffale, annoPubblicazione, titolo);
        this.autore = autore;
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", numeroPagine=" + numeroPagine +
                ", scaffale='" + scaffale + '\'' +
                '}';
    }
}
