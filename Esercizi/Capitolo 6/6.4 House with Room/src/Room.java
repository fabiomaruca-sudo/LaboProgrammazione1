public class Room {
    private String nome; //(es. "Cucina", "Camera Matrimoniale")
    private double superficieMq; //(es. 15.5)

    //COSTRUTTORI

    public Room(String nome, double superficieMq) {
        this.nome = nome;
        this.superficieMq = superficieMq;
    }


    //GETTER

    public String getNome() {
        return nome;
    }

    public double getSuperficieMq() {
        return superficieMq;
    }


    //TO STRING

    @Override
    public String toString() {
        return nome + " (" + superficieMq + " mq)";
    }
}
