import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RapidoExpressTest {

    @Test
    public void testAceptarRecorridoValido() {
        RapidoExpress transporte = new RapidoExpress();
        // 10 km está dentro del límite de 12 km
        assertTrue(transporte.aceptarRecorrido(10.0));
    }

    @Test
    public void testAceptarRecorridoLimiteExacto() {
        RapidoExpress transporte = new RapidoExpress();
        // Exactamente 12 km debe ser aceptado
        assertTrue(transporte.aceptarRecorrido(12.0));
    }

    @Test
    public void testAceptarRecorridoInvalido() {
        RapidoExpress transporte = new RapidoExpress();
        // 15 km supera el límite permitido de 12 km
        assertFalse(transporte.aceptarRecorrido(15.0));
    }

    @Test
    public void testCalcularCostoDistanciaCero() {
        RapidoExpress transporte = new RapidoExpress();
        // Base 5000 + (1500 * 0) = 5000
        assertEquals(5000.0, transporte.calcularCosto(0.0), 0.001);
    }

    @Test
    public void testCalcularCostoDistanciaNormal() {
        RapidoExpress transporte = new RapidoExpress();
        // Base 5000 + (1500 * 4 km) = 5000 + 6000 = 11000
        assertEquals(11000.0, transporte.calcularCosto(4.0), 0.001);
    }
}
