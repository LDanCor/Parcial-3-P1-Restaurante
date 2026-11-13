import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    public void testCalcularValorTotalSoloRestaurante() {
        // Producto plato fuerte: precio base 20000 + recargo frito 2000 = 22000
        Producto plato = new PlatoFuerte("P1", "Pollo", "Rico",
                20000, 20, 400, TipoPreparacion.FRITO, true);

        DetallePedido detalle = new DetallePedido(plato, 2);

        List<DetallePedido> detalles = new ArrayList<>();
        detalles.add(detalle);

        Cliente cliente = new Cliente("123", "Ana", "5551234", "ana@mail.com", "Calle 1");

        // Pedido en modalidad RESTAURANTE (sin costo de envío)
        Pedido pedido = new Pedido("PED01", LocalDate.now(), ModalidadEntrega.RESTAURANTE,
                EstadoEntrega.PENDIENTE, 0.0, cliente, new RapidoExpress(),detalles);

        assertEquals(44000.0, pedido.calcularValorTotal(), 0.001);
    }

    @Test
    public void testCalcularValorTotalConDomicilio() {
        Producto plato = new PlatoFuerte("P1", "Carne", "Buena", 10000, 15, 300, TipoPreparacion.ASADO, true);
        // Recargo asado 500 -> Precio venta unitario = 10500. Cantidad: 1 -> Subtotal = 10500
        DetallePedido detalle = new DetallePedido(plato, 1);

        List<DetallePedido> detalles = new ArrayList<>();
        detalles.add(detalle);

        Cliente cliente = new Cliente("456", "Carlos", "5555678", "carlos@mail.com", "Av Principal");

        Pedido pedido = new Pedido("PED02", LocalDate.now(), ModalidadEntrega.DOMICILIO, EstadoEntrega.PENDIENTE, 4.0, cliente,new RapidoExpress(),detalles );

        // Asignar RapidoExpress: Base 5000 + (1500 * 4 km) = 11000 de envío
        pedido.setEmpresaTransporte(new RapidoExpress());

        // Total esperado = 10500 (productos) + 11000 (envío) = 21500
        assertEquals(21500.0, pedido.calcularValorTotal(), 0.001);
    }

    @Test
    public void testCalcularValorTotalListaVacia() {
        List<DetallePedido> detalles = new ArrayList<>();
        Cliente cliente = new Cliente("789", "Lucía", "5559999", "lucia@mail.com", "Calle 3");

        Pedido pedido = new Pedido("PED03", LocalDate.now(), ModalidadEntrega.RESTAURANTE, EstadoEntrega.PENDIENTE, 0.0, cliente, new RapidoExpress(),detalles);

        assertEquals(0.0, pedido.calcularValorTotal(), 0.001);
    }

    @Test
    public void testAsignacionCorrectaDeCliente() {
        Cliente cliente = new Cliente("111", "Pedro", "5550000", "pedro@mail.com", "Carrera 5");
        List<DetallePedido> detalles = new ArrayList<>();
        Pedido pedido = new Pedido("PED04", LocalDate.now(), ModalidadEntrega.RESTAURANTE,
                EstadoEntrega.PENDIENTE, 0.0, cliente, new RapidoExpress(),detalles );

        assertEquals("Pedro", pedido.getCliente().getNombreCompleto());
        assertEquals("111", pedido.getCliente().getDocumento());
    }

    @Test
    public void testCambioEstadoPedido() {
        List<DetallePedido> detalles = new ArrayList<>();
        Pedido pedido = new Pedido("PED05", LocalDate.now(), ModalidadEntrega.RESTAURANTE, EstadoEntrega.PENDIENTE, 0.0, null, new RapidoExpress(),detalles);

        assertEquals(EstadoEntrega.PENDIENTE, pedido.getEstado());

        pedido.setEstado(EstadoEntrega.CONFIRMADO);
        assertEquals(EstadoEntrega.CONFIRMADO, pedido.getEstado());
    }
}