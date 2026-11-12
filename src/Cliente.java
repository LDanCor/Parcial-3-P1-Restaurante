public class Cliente {
    private String documento;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;

    /**
     * Metodo constructor para la clase Cliente
     * @param documento
     * @param nombreCompleto
     * @param telefono
     * @param direccion
     * @param correo
     */
    public Cliente(String documento, String nombreCompleto, String telefono, String direccion, String correo) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
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
}
