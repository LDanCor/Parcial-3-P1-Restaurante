import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String documento;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;
    /**
     * Relaciones de la clase Cliente
     */
    private List<Pedido> listClientePedido;

    /**
     * Metodo constructor para la clase Cliente
     * @param documento
     * @param nombreCompleto
     * @param telefono
     * @param direccion
     * @param correo
     */
    public Cliente(String documento, String nombreCompleto, String telefono, String direccion, String correo, List<Pedido>listClientePedido) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.listClientePedido=new ArrayList<>();
    }

    /**
     * Metodo para agregar un pedido a la lista del cliente
     * @param pedido
     */
    public void agregarPedido(Pedido pedido) {
        listClientePedido.add(pedido);
    }


    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getListClientePedido() {
        return listClientePedido;
    }

    public void setListClientePedido(List<Pedido> listClientePedido) {
        this.listClientePedido = listClientePedido;
    }
}
