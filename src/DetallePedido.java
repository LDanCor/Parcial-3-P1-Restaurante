public class DetallePedido {
    private Producto theProducto;
    private int cantidad;

    /**
     * Metodo constructor para la clase Detalle Pedido
     * @param productoPedido
     * @param cantidad
     */
    public DetallePedido(Producto productoPedido, int cantidad) {
        this.theProducto = productoPedido;
        this.cantidad = cantidad;
    }

    /**
     * Metodo para calcular el subtotal del pedido de acuerdo al producto y cantidad
     * @return
     */
    public double calcularSubtotal() {

        return theProducto.calcularPrecioVenta() * cantidad;
    }


    public Producto getProductoPedido() {
        return theProducto;
    }

    public void setProductoPedido(Producto productoPedido) {
        this.theProducto = productoPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}