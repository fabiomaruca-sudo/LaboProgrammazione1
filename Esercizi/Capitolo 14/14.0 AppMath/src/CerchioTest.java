import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CerchioTest {
    Cerchio c = new Cerchio(5);

    @Test
    void shouldReturnArea() {
        double expectedArea = 78.539;
        assertEquals(expectedArea, c.area(), 0.001);
    }

    @Test
    void shouldReturnPerimetro() {
        double expectedPerimeter = 31.415;
        assertEquals(expectedPerimeter, c.perimetro(), 0.001);
    }
}