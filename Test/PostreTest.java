import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostreTest {

    @Test
    public void testCalcularPrecioPostreConRecargo() {
        // Ajusta los parámetros según tu constructor de Postre
        Postre postre = new Postre("PO01", "Cheesecake", "Frutos rojos", 12000,
                10, 450, 10,true);

        // Comprueba que aplique el recargo correspondiente (ej. refrigeración o especial)
        double precioFinal = postre.calcularPrecioVenta();
        assertTrue(precioFinal >= 12000);
    }

    @Test
    public void testCalcularPrecioPostreEstandar() {
        Postre postre = new Postre("PO02", "Galleta", "Chispas", 4000,
                5, 200, 18,false);
        assertEquals(4000.0, postre.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void testCalidadPostreAprobada() {
        Postre postre = new Postre("PO03", "Flan", "Caramelo", 8000,
                15, 300, 5,true);
        assertTrue(postre.cumplirCondicionesCalidad());
    }

    @Test
    public void testCalidadPostreRechazada() {
        // Ejemplo: exceso de calorías o condición de calidad no cumplida en tu lógica
        Postre postre = new Postre("PO04", "Torta", "Doble chocolate", 15000,
                20, -50, 10, false);
        assertFalse(postre.cumplirCondicionesCalidad());
    }

    @Test
    public void testModificacionPrecioBase() {
        Postre postre = new Postre("PO05", "Helado", "Vainilla", 7000,
                2, 250, 5, true);
        postre.setPrecioBase(8500);
        assertEquals(8500.0, postre.getPrecioBase(), 0.001);
    }
}