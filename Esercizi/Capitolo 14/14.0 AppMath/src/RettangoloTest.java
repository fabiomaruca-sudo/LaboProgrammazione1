import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloTest {

    @ParameterizedTest
    @CsvSource(value = {
            "3.0|4.0|12.0",
            "5.0|5.0|25.0",
            "1.0|10.0|10.0",
            "2.5|4.0|10.0"
    }, delimiter = '|')
    public void testArea(double base, double altezza, double areaAttesa) {
        Rettangolo r = new Rettangolo(base, altezza);
        assertEquals(areaAttesa, r.area(), 0.001);
    }
}