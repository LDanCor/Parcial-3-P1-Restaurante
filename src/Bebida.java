public class Bebida extends Producto {
    private double volumenMl;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean requierePreparacion;

    /**
     * Metodo constructor de la clase Bebida
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precioBase
     * @param tiempoPreparacion
     * @param volumenMl
     * @param temperaturaServicio
     * @param esFria
     * @param requierePreparacion
     */
    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                  double volumenMl, double temperaturaServicio, boolean esFria, boolean requierePreparacion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.volumenMl = volumenMl;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.requierePreparacion = requierePreparacion;
    }

    /**
     * Metodo para caluccular el valor de la bebida
     * @return
     */
    @Override
    public double calcularPrecioVenta() {
        double adicional = volumenMl > 500 ? 1500 : 0;
        return getPrecioBase() + adicional;
    }

    /**
     * Metoodo para verificar las optimas condiciones de la bebida
     * @return
     */
    @Override
    public boolean cumplirCondicionesCalidad() {
        boolean volumenValido = (volumenMl >= 150 && volumenMl <= 1500);
        if (!volumenValido) return false;

        if (esFria) {
            return (temperaturaServicio >= 0 && temperaturaServicio <= 10);
        } else {
            return (temperaturaServicio >= 55 && temperaturaServicio <= 85);
        }
    }


    public double getVolumenMl() {
        return volumenMl;
    }

    public void setVolumenMl(double volumenMl) {
        this.volumenMl = volumenMl;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public boolean isEsFria() {
        return esFria;
    }

    public void setEsFria(boolean esFria) {
        this.esFria = esFria;
    }

    public boolean isRequierePreparacion() {
        return requierePreparacion;
    }

    public void setRequierePreparacion(boolean requierePreparacion) {
        this.requierePreparacion = requierePreparacion;
    }
}