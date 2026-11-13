public class RapidoExpress implements IEmpresaTransporte{
    @Override
    public double calcularCosto(double distanciaKm) {
        return 5000 + (1500 * distanciaKm);
    }

    @Override
    public boolean aceptarRecorrido(double distanciaKm) {
        return distanciaKm <= 12;
    }
}
