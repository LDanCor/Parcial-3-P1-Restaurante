import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

void main() {

    // Creo restaurante
    Restaurante restaurante = new Restaurante("Dalai Lomo", "1234", "Calle 15a");

    // Emmpresas de transporte (implementadas por IEmpresaTransporte)
    IEmpresaTransporte rapidoExpress = new RapidoExpress();
    IEmpresaTransporte entregaSegura = new EntregaSegura();

    int opcion = 0;
    int opcionDos = 0;
    int opcionTres = 0;

    while (opcion != 3) {

        opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "Seleccione según corresponda:\n" +
                        "1. Pedidos\n" +
                        "2. Reportes\n" +
                        "3. Salir\n"));

        switch (opcion) {

            case 1:

                opcionDos = 0;

                while (opcionDos != 6) {

                    opcionDos = Integer.parseInt(JOptionPane.showInputDialog(
                            "Seleccione:\n" +
                                    "1. Registrar productos\n" +
                                    "2. Registrar clientes\n" +
                                    "3. Registrar pedidos\n" +
                                    "4. Calcular valor total de un pedido\n" +
                                    "5. Consultar historial de pedidos de un cliente\n" +
                                    "6. Volver al menú principal\n"));

                    switch (opcionDos) {

                        case 1:
                            // REGISTRA PRODUCTO
                            int tipo = Integer.parseInt(JOptionPane.showInputDialog(
                                    "Tipo de producto:\n" +
                                            "1. Plato fuerte\n" +
                                            "2. Bebida\n" +
                                            "3. Postre\n"));

                            // Datos de los productos
                            String codigoProducto = JOptionPane.showInputDialog("Código del producto:");
                            String nombreProducto = JOptionPane.showInputDialog("Nombre del producto:");
                            String descripcion = JOptionPane.showInputDialog("Descripción del producto:");
                            double precioBase = Double.parseDouble(JOptionPane.showInputDialog("Precio base:"));
                            int tiempoPreparacion = Integer.parseInt(JOptionPane.showInputDialog("Tiempo de preparación (minutos):"));

                            Producto producto = null;

                            if (tipo == 1) {
                                // Datos plato fuerte
                                double pesoGramos = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos:"));

                                int opcionPrep = Integer.parseInt(JOptionPane.showInputDialog(
                                        "Tipo de preparación:\n" +
                                                "1. Asado\n" +
                                                "2. Frito\n" +
                                                "3. Horneado\n" +
                                                "4. Plancha\n"));

                                TipoPreparacion tipoPrep = TipoPreparacion.ASADO;
                                if (opcionPrep == 2) {
                                    tipoPrep = TipoPreparacion.FRITO;
                                } else if (opcionPrep == 3) {
                                    tipoPrep = TipoPreparacion.HORNEADO;
                                } else if (opcionPrep == 4) {
                                    tipoPrep = TipoPreparacion.PLANCHA;
                                }

                                int opcionCoccion = Integer.parseInt(JOptionPane.showInputDialog(
                                        "¿Superó el control de cocción?\n" +
                                                "1. Sí\n" +
                                                "2. No\n"));
                                boolean superoControl = (opcionCoccion == 1);

                                producto = new PlatoFuerte(codigoProducto, nombreProducto, descripcion, precioBase,
                                        tiempoPreparacion, pesoGramos, tipoPrep, superoControl);

                            } else if (tipo == 2) {
                                // Datos de bebidas
                                double volumenMl = Double.parseDouble(JOptionPane.showInputDialog("Volumen en ml:"));
                                double temperatura = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de servicio (°C):"));

                                int opcionFria = Integer.parseInt(JOptionPane.showInputDialog(
                                        "¿Es fría?\n" +
                                                "1. Sí\n" +
                                                "2. No\n"));
                                boolean esFria = (opcionFria == 1);

                                int opcionRequiere = Integer.parseInt(JOptionPane.showInputDialog(
                                        "¿Requiere preparación?\n" +
                                                "1. Sí\n" +
                                                "2. No\n"));
                                boolean requierePreparacion = (opcionRequiere == 1);

                                producto = new Bebida(codigoProducto, nombreProducto, descripcion, precioBase,
                                        tiempoPreparacion, volumenMl, temperatura, esFria, requierePreparacion);

                            } else if (tipo == 3) {
                                // Datos de los postres
                                double pesoPostre = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos:"));
                                double temperaturaAlm = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de almacenamiento (°C):"));

                                int opcionRefri = Integer.parseInt(JOptionPane.showInputDialog(
                                        "¿Requiere refrigeración?\n" +
                                                "1. Sí\n" +
                                                "2. No\n"));
                                boolean requiereRefrigeracion = (opcionRefri == 1);

                                producto = new Postre(codigoProducto, nombreProducto, descripcion, precioBase,
                                        tiempoPreparacion, pesoPostre, temperaturaAlm, requiereRefrigeracion);
                            }

                            if (producto == null) {
                                JOptionPane.showMessageDialog(null, "Tipo de producto incorrecto");
                            } else {
                                // El método muestra si se registró o si el código ya existe
                                restaurante.registrarProducto(producto);
                            }
                            break;

                        case 2:
                            // REGISTRA CLIENTE
                            String documento = JOptionPane.showInputDialog("Documento del cliente:");
                            String nombreCliente = JOptionPane.showInputDialog("Nombre completo del cliente:");
                            String telefono = JOptionPane.showInputDialog("Teléfono del cliente:");
                            String direccionCliente = JOptionPane.showInputDialog("Dirección del cliente:");
                            String correo = JOptionPane.showInputDialog("Correo del cliente:");

                            // Lista vacía para cliente sin pedidos
                            Cliente cliente = new Cliente(documento, nombreCliente, telefono, direccionCliente,
                                    correo, new ArrayList<>());

                            // Método para validar documento
                            restaurante.registrarCliente(cliente);
                            break;

                        case 3:
                            // REGISTRA PEDIDO
                            String codigoPedido = JOptionPane.showInputDialog("Código del pedido:");
                            String documentoPedido = JOptionPane.showInputDialog("Documento del cliente:");

                            int tipoEntrega = Integer.parseInt(JOptionPane.showInputDialog(
                                    "Modalidad de entrega:\n" +
                                            "1. En el restaurante\n" +
                                            "2. Domicilio\n"));

                            ModalidadEntrega modalidad;
                            if (tipoEntrega == 2) {
                                modalidad = ModalidadEntrega.DOMICILIO;
                            } else {
                                modalidad = ModalidadEntrega.RESTAURANTE;
                            }


                            Pedido pedido = new Pedido(codigoPedido, LocalDate.now(), modalidad,
                                    EstadoEntrega.PENDIENTE, 0, null, null, new ArrayList<>());

                            // Para agregar productos al pedido
                            int seguir = 1;
                            while (seguir == 1) {

                                String codigoBuscado = JOptionPane.showInputDialog("Código del producto a agregar:");

                                Producto productoEncontrado = null;
                                for (Producto p : restaurante.getProductos()) {
                                    if (p.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                                        productoEncontrado = p;
                                        break;
                                    }
                                }

                                if (productoEncontrado == null) {
                                    JOptionPane.showMessageDialog(null, "Producto no encontrado");
                                } else {
                                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
                                    DetallePedido detalle = new DetallePedido(productoEncontrado, cantidad);
                                    pedido.getDetalles().add(detalle);
                                    JOptionPane.showMessageDialog(null, "Producto agregado al pedido");
                                }

                                seguir = Integer.parseInt(JOptionPane.showInputDialog(
                                        "¿Desea agregar otro producto?\n" +
                                                "1. Sí\n" +
                                                "2. No\n"));
                            }

                            // Solicitar datos adicionales -domicilio-
                            if (modalidad == ModalidadEntrega.DOMICILIO) {
                                double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia en km:"));

                                //Envío: asigna empresa al pedido
                                IEmpresaTransporte empresaElegida = null;
                                String nombreEmpresa = "";

                                boolean aceptaRapido = rapidoExpress.aceptarRecorrido(distancia);
                                boolean aceptaSegura = entregaSegura.aceptarRecorrido(distancia);

                                if (aceptaRapido && aceptaSegura) {
                                    if (rapidoExpress.calcularCosto(distancia) <= entregaSegura.calcularCosto(distancia)) {
                                        empresaElegida = rapidoExpress;
                                        nombreEmpresa = "Rápido Express";
                                    } else {
                                        empresaElegida = entregaSegura;
                                        nombreEmpresa = "Entrega Segura";
                                    }
                                } else if (aceptaRapido) {
                                    empresaElegida = rapidoExpress;
                                    nombreEmpresa = "Rápido Express";
                                } else if (aceptaSegura) {
                                    empresaElegida = entregaSegura;
                                    nombreEmpresa = "Entrega Segura";
                                }

                                if (empresaElegida == null) {
                                    JOptionPane.showMessageDialog(null,
                                            "Ninguna empresa acepta un recorrido de " + distancia + " km");
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Empresa asignada: " + nombreEmpresa + "\n" +
                                                    "Costo del envío: $" + empresaElegida.calcularCosto(distancia));
                                }

                                pedido.setEmpresaTransporte(empresaElegida);
                                pedido.setDistanciaKm(distancia);
                            }

                            restaurante.registrarPedido(pedido, documentoPedido);
                            break;

                        case 4:
                            //CALCULA VALOR TOTAL
                            String codigoConsulta = JOptionPane.showInputDialog("Código del pedido:");
                            double total = restaurante.calcularValorTotalPedido(codigoConsulta);

                            // Si devuelve -1, el método ya mostró "Pedido no encontrado"
                            if (total != -1) {
                                JOptionPane.showMessageDialog(null, "El valor total del pedido es: $" + total);
                            }
                            break;

                        case 5:
                            //MUESTRA HISTORIAL DE PEDIDOS
                            String documentoHistorial = JOptionPane.showInputDialog("Documento del cliente:");
                            restaurante.consultarHistorialPedidosCliente(documentoHistorial);
                            break;

                        case 6:
                            JOptionPane.showMessageDialog(null,
                                    "Volviendo al menú principal");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Opción incorrecta");
                            break;
                    }
                }

                break;

            case 2:

                opcionTres = 0;

                JOptionPane.showMessageDialog(null,
                        "Entrando a reportes");

                while (opcionTres != 6) {

                    opcionTres = Integer.parseInt(JOptionPane.showInputDialog(
                            "Seleccione:\n" +
                                    "1. Consultar productos disponibles\n" +
                                    "2. Calcular ingresos por ventas\n" +
                                    "3. Producto más vendido\n" +
                                    "4. Categoría con mayores ingresos\n" +
                                    "5. Productos que no cumplen calidad\n" +
                                    "6. Volver al menú principal\n"));

                    switch (opcionTres) {

                        case 1:
                            //MUESTRA PRODUCTOS DISPONIBLES
                            if (restaurante.getProductos().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "No hay productos registrados");
                            } else {
                                String lista = "Productos disponibles:\n";
                                for (Producto p : restaurante.getProductos()) {
                                    lista = lista + "- " + p.getNombre() +
                                            " (Código: " + p.getCodigo() + ")" +
                                            " | Precio: $" + p.calcularPrecioVenta() + "\n";
                                }
                                JOptionPane.showMessageDialog(null, lista);
                            }
                            break;

                        case 2:
                            //CALCULA INGRESOS POR VENTAS
                            restaurante.calcularIngresosSeparados();
                            break;

                        case 3:
                            //MUESTRA PRODUCTO MÁS VENDIDO
                            restaurante.productoMasVendido();
                            break;

                        case 4:
                            //MUESTRA CATEGORÍA CON MÁS VENTAS
                            restaurante.categoriaConMayoresIngresos();
                            break;

                        case 5:
                            //MUESTRA PRODUCTOS QUE NO TIENEN BUENA CALIDAD
                            restaurante.listarProductosIncompatiblesCalidad();
                            break;

                        case 6:
                            JOptionPane.showMessageDialog(null,
                                    "Volviendo al menú principal");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Opción incorrecta");
                            break;
                    }
                }

                break;

            case 3:

                JOptionPane.showMessageDialog(null,
                        "Saliendo del programa");

                break;

            default:

                JOptionPane.showMessageDialog(null,
                        "Opción incorrecta");

                break;
        }
    }
}