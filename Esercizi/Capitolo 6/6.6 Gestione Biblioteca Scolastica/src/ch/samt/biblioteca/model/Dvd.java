package ch.samt.biblioteca.model;

public class Dvd extends ItemBiblioteca{
    private String regista;
    private int durataMinuti;

    //COSTRUTTORE

    public Dvd(String codice, String scaffale, int annoPubblicazione, String titolo, String regista, int durataMinuti) {
        super(codice, scaffale, annoPubblicazione, titolo);
        this.regista = regista;
        this.durataMinuti = durataMinuti;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "regista='" + regista + '\'' +
                ", durataMinuti=" + durataMinuti +
                ", scaffale='" + scaffale + '\'' +
                '}';
    }
}
