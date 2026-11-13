public class Postre extends Producto {
    private double pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    /**
     * Meotodo contructor de la clase Postre
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precioBase
     * @param tiempoPreparacion
     * @param pesoGramos
     * @param temperaturaAlmacenamiento
     * @param requiereRefrigeracion
     */
    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                  double pesoGramos, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    /**
     * Metodo que calcula el precio del postre
     * @return
     */
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase();
    }

    /**
     * Metodo para verificar las condiciones optimas del postre
     * @return
     */
    @Override
    public boolean cumplirCondicionesCalidad() {
        boolean pesoValido = (pesoGramos >= 80 && pesoGramos <= 500);
        if (!pesoValido) return false;


        if (requiereRefrigeracion) {
            return (temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6);
        } else {
            return (temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30);
        }
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }
}
