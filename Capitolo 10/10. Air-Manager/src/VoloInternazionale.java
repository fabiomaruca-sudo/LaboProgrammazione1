import java.time.LocalDate;

public class VoloInternazionale extends Prenotazione {
    public VoloInternazionale(String codiceVolo, LocalDate dataPartenza, double prezzoBase) {
        super(codiceVolo, dataPartenza, prezzoBase);
    }

    @Override
    public String generaTicket() {
        return "";
    }
}
