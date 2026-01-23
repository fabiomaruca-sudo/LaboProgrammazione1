public class TeslaModels extends ElectricVehicle implements Autonomus {
    public TeslaModels(String model, int battery) {
        super(model, battery);
    }

    @Override
    public String drive() {
        return "Silent acceleration...";
    }

    @Override
    public boolean canActivateAutopilot() {
        return battery >= MIN_BATTERY_FOR_AUTOPILOT;
    }

}
