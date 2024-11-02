package vista;

import java.util.Scanner;

public class MenuAdmin {
	public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Gestionar plantas");
            System.out.println("2. Gestión ");
            System.out.println("3. Eliminar un usuario");
            System.out.println("4. Modificar un usuario");
            System.out.println("5. Ver estadísticas");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    agregarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    modificarUsuario();
                    break;
                case 5:
                    verEstadisticas();
                    break;
                case 6:
                    System.out.println("Saliendo del menú administrador...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private void verUsuarios() {
        System.out.println("Opción para ver usuarios seleccionada.");
        // Implementar la lógica para ver los usuarios
    }

    private void agregarUsuario() {
        System.out.println("Opción para agregar un nuevo usuario seleccionada.");
        // Implementar la lógica para agregar un usuario
    }

    private void eliminarUsuario() {
        System.out.println("Opción para eliminar un usuario seleccionada.");
        // Implementar la lógica para eliminar un usuario
    }

    private void modificarUsuario() {
        System.out.println("Opción para modificar un usuario seleccionada.");
        // Implementar la lógica para modificar un usuario
    }

    private void verEstadisticas() {
        System.out.println("Opción para ver estadísticas seleccionada.");
        // Implementar la lógica para ver estadísticas
    }
}
