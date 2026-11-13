import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BebidaTest {

    @Test
    public void testCalcularPrecioConRecargoFria() {
        // Ajusta los parámetros según tu constructor de Bebida (ej: codigo, nombre, desc, precioBase, tiempo, ml, esAlcoholica, esFria/temperatura)
        Bebida bebida = new Bebida("B01", "Jugo Natural", "Jugo en agua", 5000,
                5, 350, 30,false, true);

        // Verifica el cálculo del precio final según la regla de negocio de Bebida
        double precioCalculado = bebida.calcularPrecioVenta();
        assertTrue(precioCalculado >= 5000);
    }

    @Test
    public void testCalcularPrecioBebidaSinRecargo() {
        Bebida bebida = new Bebida("B02", "Agua", "Botella", 3000,
                1, 500, 10, false,false);
        assertEquals(3000.0, bebida.calcularPrecioVenta(), 0.001);
    }

    @Test
    public void testControlCalidadAprobado() {
        Bebida bebida = new Bebida("B03", "Limonada", "Natural", 6000,
                5, 400, 15,false, true);
        // Ajusta el nombre del método según como lo llamaste en Producto/Bebida (ej: cumpleControlCalidad() o validarCalidad())
        assertTrue(bebida.cumplirCondicionesCalidad());
    }

    @Test
    public void testControlCalidadRechazado() {
        // Ejemplo: volumen inválido (0 ml) o temperatura inadecuada según tu validación
        Bebida bebida = new Bebida("B04", "Soda", "Sin gas", 4000,
                2, 0, 5,false, false);
        assertFalse(bebida.cumplirCondicionesCalidad());
    }

    @Test
    public void testGettersYAtributosHeredados() {
        Bebida bebida = new Bebida("B05", "Café", "Tinto", 2500, 3,
                150, 25,false, false);
        assertEquals("B05", bebida.getCodigo());
        assertEquals("Café", bebida.getNombre());
    }
}