import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        String opcionStr;
        int opcion = 0;

        do {
            String menu = "   MENÚ: EL BUEN COMER\n"
                    + "1 Registrar Cliente\n"
                    + "2 Desea: Consultar / Modificar / Eliminar Cliente\n"
                    + "3 Crear producto: Plato / Bebida / Postre)\n"
                    + "4 Consultar productos y estado de calidad\n"
                    + "5 Crear pedido y calcular total\n"
                    + "6 Consultar pedidos de un cliente\n"
                    + "7 Ingresos\n"
                    + "8 Salir\n\n"
                    + "Elige una opción:";

            opcionStr = JOptionPane.showInputDialog(null, menu, "El Buen Comer", JOptionPane.QUESTION_MESSAGE);
            if (opcionStr == null) break;

            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1: // Registrar Cliente
                    String doc = JOptionPane.showInputDialog("Documento del cliente:");
                    if (doc == null || doc.isEmpty()) break;

                    boolean existe = clientes.stream().anyMatch(c -> c.getDocumento().equals(doc));
                    if (existe) {
                        JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese documento.", "Duplicado", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    String nombre = JOptionPane.showInputDialog("Nombre completo:");
                    String tel = JOptionPane.showInputDialog("Teléfono:");
                    String correo = JOptionPane.showInputDialog("Correo:");
                    String dir = JOptionPane.showInputDialog("Dirección:");

                    clientes.add(new Cliente(doc, nombre, tel, correo, dir));
                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito!");
                    break;

                case 2: // Submenú Cliente (Consultar, Modificar, Eliminar)
                    String[] subOpcionesCliente = {"Consultar", "Modificar", "Eliminar"};
                    int subCli = JOptionPane.showOptionDialog(null, "Selecciona acción:", "Gestión de Clientes",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, subOpcionesCliente, subOpcionesCliente[0]);

                    if (subCli == 0) { // Consultar
                        String docB = JOptionPane.showInputDialog("Documento a buscar:");
                        Cliente c = clientes.stream().filter(x -> x.getDocumento().equals(docB)).findFirst().orElse(null);
                        if (c != null) {
                            JOptionPane.showMessageDialog(null, "Cliente: " + c.getNombreCompleto() + "\nTel: " + c.getTelefono() + "\nDir: " + c.getDireccion());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        }
                    } else if (subCli == 1) { // Modificar
                        String docM = JOptionPane.showInputDialog("Documento del cliente a modificar:");
                        Cliente c = clientes.stream().filter(x -> x.getDocumento().equals(docM)).findFirst().orElse(null);
                        if (c != null) {
                            c.setNombreCompleto(JOptionPane.showInputDialog("Nuevo nombre:", c.getNombreCompleto()));
                            c.setTelefono(JOptionPane.showInputDialog("Nuevo teléfono:", c.getTelefono()));
                            c.setDireccion(JOptionPane.showInputDialog("Nueva dirección:", c.getDireccion()));
                            JOptionPane.showMessageDialog(null, "¡Cliente modificado!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        }
                    } else if (subCli == 2) { // Eliminar
                        String docE = JOptionPane.showInputDialog("Documento a eliminar:");
                        if (clientes.removeIf(x -> x.getDocumento().equals(docE))) {
                            JOptionPane.showMessageDialog(null, "Cliente eliminado!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        }
                    }
                    break;

                case 3: // Crear Producto con tus constructores exactos
                    String[] tipos = {"Plato Fuerte", "Bebida", "Postre"};
                    int tipoProd = JOptionPane.showOptionDialog(null, "Selecciona el producto:", "Crear Producto",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipos, tipos[0]);

                    if (tipoProd == -1) break;
                    String cod = JOptionPane.showInputDialog("Código:");
                    String nom = JOptionPane.showInputDialog("Nombre:");
                    double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio base:"));

                    Producto nuevoProd = null;
                    if (tipoProd == 0) {
                        nuevoProd = new PlatoFuerte(cod, nom, "Delicioso", precio, 20, 500, TipoPreparacion.FRITO, true);
                    } else if (tipoProd == 1) {
                        nuevoProd = new Bebida(cod, nom, "Refrescante", precio, 5, 350, 8, false, true);
                    } else if (tipoProd == 2) {
                        nuevoProd = new Postre(cod, nom, "Dulce", precio, 10, 300, 5, true);
                    }

                    if (nuevoProd != null) {
                        productos.add(nuevoProd);
                        JOptionPane.showMessageDialog(null, "¡Producto creado con éxito!\n¿Cumple calidad?: " + nuevoProd.cumplirCondicionesCalidad());
                    }
                    break;

                case 4: // Consultar productos disponibles y los que NO cumplen calidad
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    StringBuilder sbProd = new StringBuilder("--- PRODUCTOS DISPONIBLES ---\n\n");
                    StringBuilder sbFallas = new StringBuilder("--- PRODUCTOS QUE NO CUMPLEN CALIDAD ---\n\n");
                    int fallasCount = 0;

                    for (Producto p : productos) {
                        sbProd.append("• ").append(p.getNombre()).append(" - $").append(p.getPrecioBase()).append("\n");
                        if (!p.cumplirCondicionesCalidad()) {
                            sbFallas.append("• ").append(p.getNombre()).append(" (Código: ").append(p.getCodigo()).append(")\n");
                            fallasCount++;
                        }
                    }

                    String reporteProd = sbProd.toString() + "\n" + (fallasCount > 0 ? sbFallas.toString() : "¡Todos los productos cumplen con las condiciones de calidad!");
                    JOptionPane.showMessageDialog(null, reporteProd, "Reporte de Productos", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 5: // Crear Pedido con cálculo dinámico de domicilio
                    if (clientes.isEmpty() || productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debes tener al menos un cliente y un producto registrados.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    String docCliPedido = JOptionPane.showInputDialog("Documento del cliente para el pedido:");
                    Cliente clientePedido = clientes.stream().filter(c -> c.getDocumento().equals(docCliPedido)).findFirst().orElse(null);

                    if (clientePedido == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    String[] modalidades = {"Restaurante", "Domicilio"};
                    int modIndex = JOptionPane.showOptionDialog(null, "Modalidad de entrega:", "Modalidad",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, modalidades, modalidades[0]);

                    ModalidadEntrega modalidad = (modIndex == 1) ? ModalidadEntrega.DOMICILIO : ModalidadEntrega.RESTAURANTE;
                    double distanciaKm = 0.0;

                    if (modalidad == ModalidadEntrega.DOMICILIO) {
                        try {
                            distanciaKm = Double.parseDouble(JOptionPane.showInputDialog("Distancia en kilómetros (km) para el domicilio:"));
                        } catch (Exception e) {
                            distanciaKm = 0.0;
                        }
                    }

                    // Seleccionar un producto de la lista general
                    String[] nombresProds = productos.stream().map(Producto::getNombre).toArray(String[]::new);
                    int prodIndex = JOptionPane.showOptionDialog(null, "Selecciona el producto:", "Detalle de Pedido",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, nombresProds, nombresProds[0]);

                    if (prodIndex == -1) break;
                    Producto productoSeleccionado = productos.get(prodIndex);

                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de unidades:", "1"));

                    List<DetallePedido> detalles = new ArrayList<>();
                    detalles.add(new DetallePedido(productoSeleccionado, cantidad));

                    Pedido pedido = new Pedido("PED0" + (pedidos.size() + 1), LocalDate.now(), modalidad, EstadoEntrega.PENDIENTE, distanciaKm, clientePedido, new RapidoExpress(), detalles);
                    pedidos.add(pedido);

                    JOptionPane.showMessageDialog(null, "¡Pedido creado con éxito!\nValor Total: $" + pedido.calcularValorTotal(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 6: // Consultar Pedidos de un Cliente
                    String docPed = JOptionPane.showInputDialog("Documento del cliente:");
                    StringBuilder historial = new StringBuilder("Pedidos del cliente:\n\n");
                    int contador = 0;

                    for (Pedido ped : pedidos) {
                        if (ped.getCliente().getDocumento().equals(docPed)) {
                            contador++;
                            historial.append("• Pedido ID: ").append(ped.getCodigo())
                                    .append(" | Modalidad: ").append(ped.getModalidad())
                                    .append(" | Total: $").append(ped.calcularValorTotal()).append("\n");
                        }
                    }

                    JOptionPane.showMessageDialog(null, contador > 0 ? historial.toString() : "No hay pedidos para este cliente.");
                    break;

                case 7: // Reportes de Negocio (Ingresos, Producto más vendido, Categoría más rentable)
                    if (pedidos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pedidos registrados para generar reportes.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    double ingresosRestaurante = 0.0;
                    double ingresosDomiciliosEnvio = 0.0;

                    // Mapas para unidades vendidas por producto y categoria
                    java.util.HashMap<String, Integer> unidadesPorProducto = new java.util.HashMap<>();
                    java.util.HashMap<String, Double> ingresosPorCategoria = new java.util.HashMap<>();

                    for (Pedido p : pedidos) {
                        // Separar ingresos de restaurante vs domicilios (Tarifa de envío)
                        if (p.getModalidad() == ModalidadEntrega.DOMICILIO) {
                            // Asumiendo que el costo de envío se puede aislar o calcular
                            double costoEnvio = p.calcularValorTotal() - p.calcularValorTotal(); // O la lógica que maneje tu app
                            ingresosDomiciliosEnvio += (costoEnvio > 0 ? costoEnvio : 0);
                            ingresosRestaurante += p.calcularValorTotal();
                        } else {
                            ingresosRestaurante += p.calcularValorTotal();
                        }

                        // Procesar detalles para productos y categorías
                        for (DetallePedido det : p.getDetalles()) {
                            String nombreP = det.getProductoPedido().getNombre();
                            int cant = det.getCantidad();
                            unidadesPorProducto.put(nombreP, unidadesPorProducto.getOrDefault(nombreP, 0) + cant);

                            // Determinar categoría por instancia
                            String categoria = "Otros";
                            if (det.getProductoPedido() instanceof PlatoFuerte) categoria = "Plato Fuerte";
                            else if (det.getProductoPedido() instanceof Bebida) categoria = "Bebida";
                            else if (det.getProductoPedido() instanceof Postre) categoria = "Postre";

                            double subtotalProd = det.getProductoPedido().getPrecioBase() * cant;
                            ingresosPorCategoria.put(categoria, ingresosPorCategoria.getOrDefault(categoria, 0.0) + subtotalProd);
                        }
                    }

                    // Encontrar producto más vendido
                    String productoEstrella = unidadesPorProducto.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse("Ninguno");

                    // Encontrar categoría con mayores ingresos
                    String categoriaEstrella = ingresosPorCategoria.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse("Ninguna");

                    String reporteNegocio = "--- REPORTE FINANCIERO Y ESTADÍSTICO ---\n\n"
                            + "• Ingresos del Restaurante (Venta de productos): $" + ingresosRestaurante + "\n"
                            + "• Ingresos por Tarifas de Domicilio: $" + ingresosDomiciliosEnvio + "\n"
                            + "• Ingresos Totales Generales: $" + (ingresosRestaurante + ingresosDomiciliosEnvio) + "\n\n"
                            + "• Producto más vendido (Unidades): " + productoEstrella + "\n"
                            + "• Categoría con mayores ingresos: " + categoriaEstrella + "\n";

                    JOptionPane.showMessageDialog(null, reporteNegocio, "Reportes", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 8:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 8);
    }
}