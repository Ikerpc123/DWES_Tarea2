package vista;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import modelo.Planta;
import servicios.PlantaServicio;

public class MenuPlanta {

    // Método para mostrar el menú de un invitado
    public void menuInvitado(Scanner scanner, PlantaServicio plantaServicio) {
        int opcion = -1;

        do {
            System.out.println("\n======== Menú Invitado ========");
            System.out.println("  1. Ver Plantas");
            System.out.println("  2. Volver");
            System.out.println("================================");

            // Validación de entrada
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-2): ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion >= 1 && opcion <= 2) {
                        entradaValida = true;
                    } else {
                        System.out.println("Error: Seleccione una opción válida entre 1 y 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    verPlantas(plantaServicio);
                    break;
                case 2:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 2);
    }

    // Método para ver todas las plantas
    public void verPlantas(PlantaServicio plantaServicio) {
        Set<Planta> plantas = plantaServicio.obtenerTodas();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas registradas.");
        } else {
            System.out.println("\n======== Lista de Plantas ========");
            for (Planta planta : plantas) {
                System.out.println("Código: " + planta.getCodigo());
                System.out.println("Nombre Común: " + planta.getNombreComun());
                System.out.println("Nombre Científico: " + planta.getNombreCientifico());
                System.out.println("----------------------------------");
            }
        }
    }

    // Método para insertar una nueva planta
    public void insertarPlanta(PlantaServicio plantaServicio) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Insertar Nueva Planta ---");
        System.out.print("Ingrese el código de la planta: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Ingrese el nombre común de la planta: ");
        String nombreComun = scanner.nextLine().trim();

        System.out.print("Ingrese el nombre científico de la planta: ");
        String nombreCientifico = scanner.nextLine().trim();

        Planta nuevaPlanta = new Planta(codigo, nombreComun, nombreCientifico);

        boolean exito = plantaServicio.agregar(nuevaPlanta);
        if (exito) {
            System.out.println("Planta insertada correctamente.");
        } else {
            System.out.println("Error al insertar la planta. Verifique los datos e intente nuevamente.");
        }
    }

    // Método para gestionar plantas
    public void gestionarPlanta(PlantaServicio plantaServicio) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======== Gestión de Plantas ========");
            System.out.println("  1. Insertar una nueva planta");
            System.out.println("  2. Modificar una planta");
            System.out.println("  3. Salir");
            System.out.println("====================================");

            // Validación de entrada
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-3): ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion >= 1 && opcion <= 3) {
                        entradaValida = true;
                    } else {
                        System.out.println("Error: Seleccione una opción válida entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    insertarPlanta(plantaServicio);
                    break;
                case 2:
                    modificarPlanta(plantaServicio);
                    break;
                case 3:
                    System.out.println("Saliendo de la gestión de plantas...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    // Método para modificar una planta
    public void modificarPlanta(PlantaServicio plantaServicio) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Modificar Planta ---");
        System.out.print("Ingrese el código de la planta a modificar: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Ingrese el nuevo nombre común de la planta: ");
        String nuevoNombreComun = scanner.nextLine().trim();

        System.out.print("Ingrese el nuevo nombre científico de la planta: ");
        String nuevoNombreCientifico = scanner.nextLine().trim();

        Planta planta = new Planta(codigo, nuevoNombreComun, nuevoNombreCientifico);

        boolean exito = plantaServicio.actualizar(planta);
        if (exito) {
            System.out.println("Planta actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la planta. Verifique los datos ingresados.");
        }
    }
}
