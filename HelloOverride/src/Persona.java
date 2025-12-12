import java.util.Objects;

public class Persona {
    private String nome;
    private int eta;
    private String AVS;


    public Persona(String nome, int eta, String AVS) {
        this.nome = nome;
        this.eta = eta;
        this.AVS = AVS;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getAVS() {
        return AVS;
    }

    public void setAVS(String AVS) {
        this.AVS = AVS;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nome, persona.nome) && Objects.equals(eta, persona.eta) && Objects.equals(AVS, persona.AVS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, eta, AVS);
    }
}
