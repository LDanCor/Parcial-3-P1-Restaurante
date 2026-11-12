import java.util.ArrayList;
import java.util.List;

    public class Restaurante {
        private String nombre;
        private String nit; // Tipo String por buenas prácticas
        private String direccion;
        /**
         * Relaciones de la clase Restaurante
         */
        private List<Cliente> clientes;
        private List<Producto> productos;
        private List<Pedido> pedidos;

        /**
         * Metodo constructor para la clase Restaurante
         * @param nombre
         * @param nit
         * @param direccion
         */
        public Restaurante(String nombre, String nit, String direccion) {
            this.nombre = nombre;
            this.nit = nit;
            this.direccion = direccion;
            this.clientes = new ArrayList<>();
            this.productos = new ArrayList<>();
            this.pedidos = new ArrayList<>();
        }

        /**
         * Metodo para agregar un producto verificando duplicados
         * @param producto
         * @return
         */
        public boolean registrarProducto(Producto producto) {
            for (Producto p : productos) {
                if (p.getCodigo().equalsIgnoreCase(producto.getCodigo())) {
                    System.out.println("Error: Ya existe un producto con el código " + producto.getCodigo());
                    return false;
                }
            }
            productos.add(producto);
            System.out.println("Producto registrado exitosamente.");
            return true;
        }

        /**
         * Metodo para resgitrar un cliente verficando que no hayan documentos duplicados
         * @param cliente
         * @return
         */
        public boolean registrarCliente(Cliente cliente) {
            for (Cliente c : clientes) {
                if (c.getDocumento().equals(cliente.getDocumento())) {
                    System.out.println("Error: Ya existe un cliente con el documento " + cliente.getDocumento());
                    return false;
                }
            }
            clientes.add(cliente);
            System.out.println("Cliente registrado exitosamente.");
            return true;
        }

        /**
         * Metodo para registrar un pedido
         * @param pedido
         * @param documentoCliente
         * @return
         */
        public boolean registrarPedido(Pedido pedido, String documentoCliente) {

            /**
             * Busca si el cliente està registrado
             */
            Cliente clienteEncontrado = null;
            for (Cliente c : clientes) {
                if (c.getDocumento().equals(documentoCliente)) {
                    clienteEncontrado = c;
                    break;
                }
            }
            if (clienteEncontrado == null) {
                System.out.println("El cliente con documento " + documentoCliente + " no está registrado.");
                return false;
            }

            /**
             * Verifica que se cumplan las condiciones minimas del pedido
             */
            for (DetallePedido detalle : pedido.getDetalles()) {
                if (!detalle.getProductoPedido().cumplirCondicionesCalidad()) {
                    System.out.println("El producto " + detalle.getProductoPedido().getNombre() + " no cumple con las condiciones de calidad.");
                    return false;
                }
            }

            /**
             * Si es domicilio verifica si la empresa acepta o no el domicilio
             */
            if (pedido.getModalidad() == ModalidadEntrega.DOMICILIO) {
                if (pedido.getEmpresaTransporte() != null) {
                    if (!pedido.getEmpresaTransporte().aceptarRecorrido(pedido.getDistanciaKm())) {
                        System.out.println("La empresa de transporte no acepta un recorrido de " + pedido.getDistanciaKm() + " km.");
                        return false;
                    }
                } else {
                    System.out.println("Pedido a domicilio sin empresa de transporte asignada.");
                    return false;
                }
            }

            /**
             * Si cumple las condiciones registra el pedido
             */
            pedido.setCliente(clienteEncontrado);
            clienteEncontrado.agregarPedido(pedido);
            pedidos.add(pedido);
            System.out.println("Pedido registrado exitosamente.");
            return true;
        }

        /**
         * Metodo para calcular el valor total del pedido filtrando por codigo de pedido
         * @param codigoPedido
         * @return
         */
        public double calcularValorTotalPedido(String codigoPedido) {
            for (Pedido p : pedidos) {
                if (p.getCodigo().equalsIgnoreCase(codigoPedido)) {
                    return p.calcularValorTotal();
                }
            }
            System.out.println("Pedido no encontrado.");
            return -1;
        }

        /**
         * Metodo para consultar el historial de pedidos de un cliente
         * @param documentoCliente
         */
        public void consultarHistorialPedidosCliente(String documentoCliente) {
            Cliente clienteEncontrado = null;
            for (Cliente c : clientes) {
                if (c.getDocumento().equals(documentoCliente)) {
                    clienteEncontrado = c;
                    break;
                }
            }

            if (clienteEncontrado == null) {
                System.out.println("Cliente no encontrado.");
                return;
            }

            System.out.println("\n    Historial de Pedidos para: " + clienteEncontrado.getNombreCompleto());
            List<Pedido> historial = clienteEncontrado.getListClientePedido();

            if (historial.isEmpty()) {
                System.out.println("El cliente no tiene pedidos.");
                return;
            }

            for (Pedido p : historial) {
                System.out.println("Código: " + p.getCodigo() +
                        " | Fecha: " + p.getFecha() +
                        " | Estado: " + p.getEstado() +
                        " | Total: $" + p.calcularValorTotal());
            }
        }

        /**
         * Metodo para buscar productos fuera de los estandares de calidad
         */
        public void listarProductosIncompatiblesCalidad() {
            System.out.println("\n   Productos que NO cumplen condiciones de calidad");
            boolean encontrados = false;
            for (Producto p : productos) {
                if (!p.cumplirCondicionesCalidad()) {
                    System.out.println(p.getNombre() + " Código: " + p.getCodigo());
                    encontrados = true;
                }
            }
            if (!encontrados) {
                System.out.println("Todos los productos cumplen con los estándares de calidad.");
            }
        }

        /**
         * Metodo para mostrar los ingresos separados por concepto de venta
         */
        public void calcularIngresosSeparados() {
            double ingresosRestaurante = 0;
            double ingresosDomicilios = 0;

            for (Pedido p : pedidos) {
                // Sumar el valor de los productos del pedido
                double subtotalProductos = 0;
                for (DetallePedido d : p.getDetalles()) {
                    subtotalProductos += d.calcularSubtotal();
                }
                ingresosRestaurante += subtotalProductos;

                // Si el pedido fue a domicilio, sumar el costo del envío cobrado
                if (p.getModalidad() == ModalidadEntrega.DOMICILIO && p.getEmpresaTransporte() != null) {
                    ingresosDomicilios += p.getEmpresaTransporte().calcularCosto(p.getDistanciaKm());
                }
            }
            System.out.println("   Ingresos");
            System.out.println("Ingresosdel restaurante: $" + ingresosRestaurante);
            System.out.println("Ingresos por domicilios: $" + ingresosDomicilios);
            System.out.println("Ingresos Totales: $" + (ingresosRestaurante + ingresosDomicilios));
        }

        /**
         * Metodo para filtrar los productos mas vendidos
         */
        public void productoMasVendido() {
            if (pedidos.isEmpty()) {
                System.out.println("\nNo hay pedidos registrados para calcular el producto más vendido.");
                return;
            }

            java.util.Map<String, Integer> ventasPorProducto = new java.util.HashMap<>();
            java.util.Map<String, String> nombresProductos = new java.util.HashMap<>();

            for (Pedido p : pedidos) {
                for (DetallePedido d : p.getDetalles()) {
                    String codigo = d.getProductoPedido().getCodigo();
                    int cantidad = d.getCantidad();

                    ventasPorProducto.put(codigo, ventasPorProducto.getOrDefault(codigo, 0) + cantidad);
                    nombresProductos.put(codigo, d.getProductoPedido().getNombre());
                }
            }

            String codigoTop = null;
            int maxUnidades = -1;
            for (var entry : ventasPorProducto.entrySet()) {
                if (entry.getValue() > maxUnidades) {
                    maxUnidades = entry.getValue();
                    codigoTop = entry.getKey();
                }
            }

            System.out.println("\n  Producto Estrella");
            if (codigoTop != null) {
                System.out.println("El producto más vendido es: " + nombresProductos.get(codigoTop) +
                        " con un total de " + maxUnidades + " unidades.");
            }
        }

        /**
         * Metodo para determinar que categoria genera mayores ingresos
         */
        public void categoriaConMayoresIngresos() {
            double ingresosPlatos = 0;
            double ingresosBebidas = 0;
            double ingresosPostres = 0;

            for (Pedido p : pedidos) {
                for (DetallePedido d : p.getDetalles()) {
                    double subtotal = d.calcularSubtotal();
                    Producto prod = d.getProductoPedido();

                    if (prod instanceof PlatoFuerte) {
                        ingresosPlatos += subtotal;
                    } else if (prod instanceof Bebida) {
                        ingresosBebidas += subtotal;
                    } else if (prod instanceof Postre) {
                        ingresosPostres += subtotal;
                    }
                }
            }

            System.out.println("\n--- Ingresos por Categoría ---");
            System.out.println("Platos Fuertes: $" + ingresosPlatos);
            System.out.println("Bebidas: $" + ingresosBebidas);
            System.out.println("Postres: $" + ingresosPostres);

            // Determinar cuál es mayor
            String mayorCategoria = "Platos Fuertes";
            double maxIngresos = ingresosPlatos;

            if (ingresosBebidas > maxIngresos) {
                maxIngresos = ingresosBebidas;
                mayorCategoria = "Bebidas";
            }
            if (ingresosPostres > maxIngresos) {
                maxIngresos = ingresosPostres;
                mayorCategoria = "Postres";
            }

            System.out.println("🏆 La categoría que genera mayores ingresos es: " + mayorCategoria + " con $" + maxIngresos);
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNit() {
            return nit;
        }

        public void setNit(String nit) {
            this.nit = nit;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public List<Cliente> getClientes() {
            return clientes;
        }

        public void setClientes(List<Cliente> clientes) {
            this.clientes = clientes;
        }

        public List<Producto> getProductos() {
            return productos;
        }

        public void setProductos(List<Producto> productos) {
            this.productos = productos;
        }

        public List<Pedido> getPedidos() {
            return pedidos;
        }

        public void setPedidos(List<Pedido> pedidos) {
            this.pedidos = pedidos;
        }
    }



