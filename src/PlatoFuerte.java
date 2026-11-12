public class PlatoFuerte extends Producto{
    private double pesoGramos;
    private TipoPreparacion tipoPrep;
    private boolean superoControlCoccion;

    /**
     * Metodo constructor para la clase Prodcuto
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precioBase
     * @param tiempoPreparacion
     * @param pesoGramos
     * @param tipoPrep
     * @param superoControlCoccion
     */
    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase,
                       int tiempoPreparacion, double pesoGramos, TipoPreparacion tipoPrep,
                       boolean superoControlCoccion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.tipoPrep = tipoPrep;
        this.superoControlCoccion = superoControlCoccion;
    }

    /**
     * Metodo para calcular el precio segun su preparacion
     * @return
     */
    @Override
    public double calcularPrecioVenta() {
        double recargo = 0;

        switch (tipoPrep) {
            case FRITO:
                recargo = 2000;
                break;
            case PLANCHA:
                recargo = 1500;
                break;
            case HORNEADO:
                recargo = 1000;
                break;
            case ASADO:
                recargo = 500; // O 0 si no tiene costo adicional
                break;
        }

        return getPrecioBase() + recargo;
    }

    /**
     * Metodo para verficar las condiciones optimas del plato
     * @return
     */
    @Override
    public boolean cumplirCondicionesCalidad() {
        boolean pesoValido = (pesoGramos >= 200 && pesoGramos <= 900);
        boolean tiempoValido = (getTiempoPreparacion() >= 10 && getTiempoPreparacion()<= 60);

        return pesoValido && tiempoValido && superoControlCoccion;
    }


    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public TipoPreparacion getTipoPrep() {
        return tipoPrep;
    }

    public void setTipoPrep(TipoPreparacion tipoPrep) {
        this.tipoPrep = tipoPrep;
    }

    public boolean isSuperoControlCoccion() {
        return superoControlCoccion;
    }

    public void setSuperoControlCoccion(boolean superoControlCoccion) {
        this.superoControlCoccion = superoControlCoccion;
    }
}




