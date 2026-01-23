import java.util.Date;

public class PrestitoLibro extends Prodotto {
    private Date dataScadenza;

    public PrestitoLibro(String codice, Date creazione, Date dataScadenza, Date dataScadenza1) {
        super(codice, creazione, dataScadenza);
        this.dataScadenza = dataScadenza1;
    }

    @Override
    public Date scadenzaProdotto(Date scadenza) {
        return scadenza.getDay();
    }
}
