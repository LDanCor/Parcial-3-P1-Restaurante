import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cliente> clientes = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        int opcion;
        do {
            System.out.println("\n--- MENÚ DE PRUEBAS: EL BUEN COMER ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Crear Plato Fuerte y probar control de calidad");
            System.out.println("3. Crear Pedido y calcular valor total");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.print("Documento: ");
                    String doc = scanner.nextLine();
                    System.out.print("Nombre completo: ");
                    String nombre = scanner.nextLine();

                    Cliente cliente = new Cliente(doc, nombre, "5550000", "correo@mail.com", "Carrera 5");
                    clientes.add(cliente);
                    System.out.println("¡Cliente registrado con éxito!");
                    break;

                case 2:
                    System.out.println("\n-- CREAR PRODUCTO --");
                    System.out.println("1. Plato Fuerte");
                    System.out.println("2. Bebida");
                    System.out.println("3. Postre");
                    System.out.print("Elige tipo de producto: ");
                    int tipoProd = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Código: ");
                    String cod = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nom = scanner.nextLine();
                    System.out.print("Precio base: ");
                    double precio = scanner.nextDouble();

                    if (tipoProd == 1) {
                        PlatoFuerte plato = new PlatoFuerte(cod, nom, "Delicioso", precio, 20,
                                500, TipoPreparacion.FRITO, true);
                        System.out.println("¡Plato Fuerte creado! ¿Cumple calidad?: " + plato.cumplirCondicionesCalidad());
                    } else if (tipoProd == 2) {
                        Bebida bebida = new Bebida(cod, nom, "Refrescante", precio, 5, 350,
                                8,false, true);
                        System.out.println("Bebida creada, ¿Cumple calidad?: " + bebida.cumplirCondicionesCalidad());
                    } else if (tipoProd == 3) {
                        Postre postre = new Postre(cod, nom, "Dulce", precio, 10,
                                300, 5,true);
                        System.out.println("¡Postre creado! ¿Cumple calidad?: " + postre.cumplirCondicionesCalidad());
                    }
                    break;

                case 3:
                    if (clientes.isEmpty()) {
                        System.out.println("¡Primero debes registrar al menos un cliente (Opción 1)!");
                        break;
                    }
                    Cliente c = clientes.get(0);
                    Producto p = new PlatoFuerte("P01", "Bandeja Paisa", "Plato típico",
                            25000, 25, 600, TipoPreparacion.FRITO, true);

                    List<DetallePedido> detalles = new ArrayList<>();
                    detalles.add(new DetallePedido(p, 2)); // 2 platos

                    Pedido pedido = new Pedido("PED01", LocalDate.now(), ModalidadEntrega.RESTAURANTE,
                            EstadoEntrega.PENDIENTE, 0.0, c, new RapidoExpress(), detalles);
                    pedidos.add(pedido);

                    System.out.println("Pedido creado para " + c.getNombreCompleto());
                    System.out.println("Valor total calculado: $" + pedido.calcularValorTotal());
                    break;

                case 4:
                    System.out.println("Saliendo del menú de pruebas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}