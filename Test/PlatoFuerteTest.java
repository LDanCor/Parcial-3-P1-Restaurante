import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlatoFuerteTest {

    @Test
    public void testCalcularPrecioVentaFrito() {
        // Precio base 20000 + recargo FRITO (2000) = 22000
        PlatoFuerte plato = new PlatoFuerte("P01", "Carne Frita", "Deliciosa", 20000, 20, 500, TipoPreparacion.FRITO, true);
        assertEquals(22000.0, plato.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void testCalcularPrecioVentaAsado() {
        // Precio base 25000 + recargo ASADO (500) = 25500
        PlatoFuerte plato = new PlatoFuerte("P02", "Pollo Asado", "Jugoso", 25000, 30, 600, TipoPreparacion.ASADO, true);
        assertEquals(25500.0, plato.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void testCumplirCondicionesCalidadExitoso() {
        // Peso en rango (500g), tiempo en rango (25 min), cocción aprobada (true)
        PlatoFuerte plato = new PlatoFuerte("P03", "Bife", "Buen punto", 30000, 25, 500, TipoPreparacion.PLANCHA, true);
        assertTrue(plato.cumplirCondicionesCalidad());
    }

    @Test
    public void testCumplirCondicionesCalidadPesoInvalido() {
        // Peso fuera de rango (150g es menor al mínimo de 200g)
        PlatoFuerte plato = new PlatoFuerte("P04", "Porción pequeña", "Ligera", 15000, 15, 150, TipoPreparacion.HORNEADO, true);
        assertFalse(plato.cumplirCondicionesCalidad());
    }

    @Test
    public void testCumplirCondicionesCalidadCoccionFallida() {
        // Control de cocción no superado (false)
        PlatoFuerte plato = new PlatoFuerte("P05", "Plato Crudo", "Mal cocinado", 20000, 20, 400, TipoPreparacion.FRITO, false);
        assertFalse(plato.cumplirCondicionesCalidad());
    }
}