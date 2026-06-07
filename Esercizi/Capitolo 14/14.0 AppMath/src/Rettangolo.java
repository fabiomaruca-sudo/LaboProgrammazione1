public class Rettangolo extends Forma{
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        if (base <= 0 || altezza <= 0) {
            throw new IllegalArgumentException();
        }
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }

    @Override
    public double perimetro() {
        return 2 * (base + altezza);
    }
}
