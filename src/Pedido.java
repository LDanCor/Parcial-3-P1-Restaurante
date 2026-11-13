import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private ModalidadEntrega modalidad;
    private EstadoEntrega estado;
    private double distanciaKm;
    /**
     * Relaciones de la clase Pedido
     */
    private Cliente cliente;
    private List<DetallePedido> detalles;
    private IEmpresaTransporte empresaTransporte;

    /**
     * Metodo constructor de la clase Pedido
     * @param codigo
     * @param fecha
     * @param modalidad
     * @param estado
     * @param distanciaKm
     * @param cliente
     * @param empresaTransporte
     * @param detalles
     */
    public Pedido(String codigo, LocalDate fecha, ModalidadEntrega modalidad, EstadoEntrega estado,
                  double distanciaKm, Cliente cliente, IEmpresaTransporte empresaTransporte,
                  List<DetallePedido> detalles) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidad = modalidad;
        this.estado = estado;
        this.distanciaKm = distanciaKm;
        this.cliente = cliente;
        this.empresaTransporte = empresaTransporte;
        this.detalles = detalles;
    }

    /**
     * Metodo para calcular el valor total de venta del pedido
     * @return
     */
    public double calcularValorTotal() {
        double totalProductos = 0;

        for (DetallePedido detalle : detalles) {
            totalProductos += detalle.calcularSubtotal();
        }
        double costoEnvio = 0;
        if (modalidad == ModalidadEntrega.DOMICILIO && empresaTransporte != null) {
            costoEnvio = empresaTransporte.calcularCosto(distanciaKm);
        }
        return totalProductos + costoEnvio;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ModalidadEntrega getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadEntrega modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoEntrega getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntrega estado) {
        this.estado = estado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public IEmpresaTransporte getEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(IEmpresaTransporte empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }
}
