public class EntregaSegura implements IEmpresaTransporte{

    @Override
    public double calcularCosto(double distanciaKm) {
        double costo = 7000 + (1000 * distanciaKm);
        if (distanciaKm > 5) {
            costo += 3000;
        }
        return costo;
    }

    @Override
    public boolean aceptarRecorrido(double distanciaKm) {
        return distanciaKm <= 20;
    }
}
