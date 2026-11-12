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
         * Metodo para registrar un cliente
         * @param cliente
         */
        public void registrarCliente(Cliente cliente) {
            clientes.add(cliente);
        }

        /**
         * Metodo para registrar un producto
         * @param producto
         */
        public void registrarProducto(Producto producto) {
            productos.add(producto);
        }

        /**
         * Metodo para registrar pedido
         * @param pedido
         */
        public void registrarPedido(Pedido pedido) {
            pedidos.add(pedido);
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
