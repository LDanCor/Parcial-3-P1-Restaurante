import javax.swing.*;

void main() {
    int opcion = 0;
    int opcionDos = 0;

    while (opcion != 4) {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "Ingrese la categoría\n" +
                        "1. Operador\n" +
                        "2. Administrador\n" +
                        "3. Reportes\n" +
                        "4. Salir\n"));
        switch (opcion) {
            case 1:

                while (opcionDos != 2){

                    opcionDos = Integer.parseInt(JOptionPane.showInputDialog(
                            "Seleccione\n" +
                                    "1. Registrar película\n" +
                                    "2. Salir\n"));
                    switch (opcionDos){
                        case 1:
                            opcionDos = 0;
                            JOptionPane.showMessageDialog(null, "Registrado correctamente");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción incorrecta");
                            break;
                    }

                }


                opcionDos = 0;

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:
                JOptionPane.showMessageDialog(null,
                        "Finalizando programa");
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Opción incorrecta. Intente con otro número válido");
                break;
        }
    }
}