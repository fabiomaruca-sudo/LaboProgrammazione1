public class ElectricTruck extends ElectricVehicle {
    private int cargoWeight;

    public ElectricTruck(String model, int battery, int cargoWeight) {
        super(model, battery);
        this.cargoWeight = cargoWeight;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    @Override
    public String drive() {
        return "Heavy transport moving [weight] kg...";
    }
}
