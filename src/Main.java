
import javax.swing.*;

void main() {

    int opcion = 0;
    int opcionDos = 0;
    int opcionTres = 0;

    while (opcion != 3) {

        opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "Seleccione según corresponda:\n" +
                        "1. Pedidos\n" +
                        "2. Reportes\n" +
                        "3. Salir\n"));

        switch (opcion) {

            case 1:

                opcionDos = 0;

                while (opcionDos != 6) {

                    opcionDos = Integer.parseInt(JOptionPane.showInputDialog(
                            "Seleccione:\n" +
                                    "1. Registrar productos\n" +
                                    "2. Registrar clientes\n" +
                                    "3. Registrar pedidos\n" +
                                    "4. Calcular valor total de un pedido\n" +
                                    "5. Consultar historial de pedidos de un cliente\n" +
                                    "6. Volver al menú principal\n"));

                    switch (opcionDos) {

                        case 1:
                            JOptionPane.showMessageDialog(null,
                                    "Registrado productos");
                            break;

                        case 2:
                            JOptionPane.showMessageDialog(null,
                                    "Registrado clientes");
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null,
                                    "Registrado pedidos");
                            break;

                        case 4:
                            JOptionPane.showMessageDialog(null,
                                    "Calculando valor total");
                            break;

                        case 5:
                            JOptionPane.showMessageDialog(null,
                                    "Consultando historial pedidos cliente");
                            break;

                        case 6:
                            JOptionPane.showMessageDialog(null,
                                    "Volviendo al menú principal");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Opción incorrecta");
                            break;
                    }
                }

                break;

            case 2:

                opcionTres = 0;

                JOptionPane.showMessageDialog(null,
                        "Entrando a reportes");



                while (opcionTres != 3) {

                    opcionTres = Integer.parseInt(JOptionPane.showInputDialog(
                            "Seleccione:\n" +
                                    "1. Consultar productos disponibles\n" +
                                    "2. Calcular ingresos por ventas\n" +
                                    "3. Volver al menú principal\n"));

                    switch (opcionTres) {

                        case 1:
                            JOptionPane.showMessageDialog(null,
                                    "Consultando productos disponibles");
                            break;

                        case 2:
                            JOptionPane.showMessageDialog(null,
                                    "Calculando ingresos por ventas");
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null,
                                    "Volviendo al menú principal");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Opción incorrecta");
                            break;
                    }
                }

                break;


            case 3:

                JOptionPane.showMessageDialog(null,
                        "Saliendo del programa");

                break;

            default:

                JOptionPane.showMessageDialog(null,
                        "Opción incorrecta");

                break;
        }
    }
}
