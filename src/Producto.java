public abstract  class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private int tiempoPreparacion;

    /**
     * Metodo constructor para la clase Producto
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precioBase
     * @param tiempoPreparacion
     */
    public Producto(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /**
     * Metodos abstractos de la clase padre Producto
     * @return
     */
    public abstract double calcularPrecioVenta();
    public abstract boolean cumplirCondicionesCalidad();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
