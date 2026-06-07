public abstract class ElectricVehicle {
    protected String model;
    protected int battery;

    public ElectricVehicle(String model, int battery) {
        this.model = model;
        if (battery < 0 || battery > 100) {
            throw  new IllegalArgumentException("Battery out of range");
        }
        this.battery = battery;
    }

    public int charge(int amount) {
        if (amount < 0) {
            return battery;
        }

        battery += amount;
        if (battery > 100) {
            battery = 100;
        }
        return battery;
    }

    abstract public String drive();
}
