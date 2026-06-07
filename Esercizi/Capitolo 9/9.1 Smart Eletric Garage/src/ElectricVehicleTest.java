import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricVehicleTest {

    @Test
    void shouldIncreaseBatteryLevelWhenAmountIsPositive() {
        ElectricTruck car = new ElectricTruck("Patrizio", 50, 100);
        int batteryAdder = 10;
        int expectedBatteryLevel = 60;

        int actualBatteryLevel = car.charge(batteryAdder);
        assertEquals(expectedBatteryLevel, actualBatteryLevel, "Livello di batteria aspettato: 60");
    }

    @Test
    void shouldCapBatteryAtOneHundredWhenOvercharged() {
        ElectricTruck car = new ElectricTruck("Patrizio", 50, 100);
        int batteryAdder = 101;
        int expectedBatteryLevel = 100;

        int actualBatteryLevel = car.charge(batteryAdder);
        assertEquals(expectedBatteryLevel, actualBatteryLevel, "Livello di batteria aspettato: 100");
    }

    @Test
    void shouldNotChangeBatteryLevelWhenAmountIsNegative() {
        ElectricTruck car = new ElectricTruck("Patrizio", 50, 100);
        int batteryAdder = -101;
        int expectedBatteryLevel = 50;

        int actualBatteryLevel = car.charge(batteryAdder);
        assertEquals(expectedBatteryLevel, actualBatteryLevel, "Livello di batteria aspettato: 50");
    }
}