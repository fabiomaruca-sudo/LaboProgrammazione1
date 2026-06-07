public class Alimento {
    private String nome;
    private  String categoria;
    private String data_scadenza;
    private int quantita;

    //COSTRUTTORI
    public Alimento(String nome, String categoria, String data_scadenza, int quantita) {
        this.nome = nome;
        this.categoria = categoria;
        this.data_scadenza = data_scadenza;
        this.quantita = quantita;
    }


    //GETTER E SETTER

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(String data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Alimento{" +
                "nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", data_scadenza='" + data_scadenza + '\'' +
                ", quantita=" + quantita +
                '}';
    }
}
