import java.util.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Prodotto {
    private String codice;
    private Date creazione;
    private Date dataScadenza;

    public Prodotto(String codice, Date creazione, Date dataScadenza) {
        this.codice = codice;
        this.creazione = creazione;
        this.dataScadenza = dataScadenza;
    }

    public String getCodice() {
        return codice;
    }

    public Date getCreazione() {
        return creazione;
    }

    public void setCreazione(Date creazione) {
        this.creazione = creazione;
    }

    public abstract Date scadenzaProdotto(Date scadenza);

    public boolean Scaduto(Date scadenza) {;
        return dataScadenza.after(scadenza);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return Objects.equals(codice, prodotto.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codice);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "codice='" + codice + '\'' +
                ", creazione=" + creazione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
