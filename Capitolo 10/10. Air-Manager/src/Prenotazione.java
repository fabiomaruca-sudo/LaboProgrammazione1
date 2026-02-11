import java.time.LocalDate;

public abstract class Prenotazione implements Documentabile {
    private String codiceVolo;
    private LocalDate dataPartenza;
    double prezzoBase;

    public Prenotazione(String codiceVolo, LocalDate dataPartenza, double prezzoBase) {
        this.codiceVolo = codiceVolo;
        this.dataPartenza = dataPartenza;
        this.prezzoBase = prezzoBase;
    }

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(LocalDate dataPartenza) {
        if  (dataPartenza.isBefore(LocalDate.now())) {throw new IllegalArgumentException("Data non valida, deve essere superiore ad oggi");}
        this.dataPartenza = dataPartenza;
    }

    public String getCodiceVolo() {
        return codiceVolo;
    }

    public void setCodiceVolo(String codiceVolo) {
        int contatore = 0;
        if (codiceVolo.length() != 5) {return;}
        for (char lettera : codiceVolo.toCharArray()) {
            if (!(Character.isLetter(lettera) && contatore < 3 || Character.isDigit(lettera) && contatore <= 3)) {
                throw new IllegalArgumentException("Valore non valido, 2 lettere e 3 numeri!");
            }
            contatore++;
        }
        this.codiceVolo = codiceVolo;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(double prezzoBase) {
        if (prezzoBase <= 0.0) {throw new IllegalArgumentException("Prezzo non valido! Deve essere maggiore di 0");}
        this.prezzoBase = prezzoBase;
    }


}
