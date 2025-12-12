package ch.samt.biblioteca.model;

import java.util.Objects;

public class ItemBiblioteca {
    private String codice;
    private String titolo;
    private int annoPubblicazione;
    protected String scaffale;

    //COSTRUTTORE
    public ItemBiblioteca(String codice, String scaffale, int annoPubblicazione, String titolo) {
        this.codice = codice;
        this.scaffale = scaffale;
        this.annoPubblicazione = annoPubblicazione;
        this.titolo = titolo;
    }

    //GETTER
    public String getCodice() {
        return codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getScaffale() {
        return scaffale;
    }

    //SETTER
    public void setScaffale(String scaffale) {
        this.scaffale = scaffale;
    }

    //OVERRIDE
    @Override
    public String toString() {
        return "ItemBiblioteca{" +
                "codice='" + codice + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", scaffale='" + scaffale + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemBiblioteca that = (ItemBiblioteca) o;
        return Objects.equals(codice, that.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codice);
    }
}
