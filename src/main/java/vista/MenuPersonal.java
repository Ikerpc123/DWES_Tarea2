package vista;

import java.util.InputMismatchException;
import java.util.Scanner;
import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuPersonal {
    
    private PlantaServicio plantaServicio;
    private EjemplarServicio ejemplarServicio;
    private MensajeServicio mensajeServicio;
    private CredencialServicio credencialServicio;
    private PersonaServicio personaServicio;
    private String usuario;

    // Constructor vacío
    public MenuPersonal() {}

    // Constructor con todos los servicios y el usuario autenticado
    public MenuPersonal(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, 
                        CredencialServicio credencialServicio, PersonaServicio personaServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.credencialServicio = credencialServicio;
        this.personaServicio = personaServicio;
        this.usuario = usuario;
    }

    // Método para mostrar el menú del personal
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======== Menú Personal ========");
            System.out.println("  1. Gestionar ejemplares");
            System.out.println("  2. Gestionar mensajes");
            System.out.println("  3. Cerrar Sesión");
            System.out.println("================================");

            // Validación de entrada del usuario
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

            // Creación de los menús de subgestión
            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
            MenuMensaje menuMensajes = new MenuMensaje(mensajeServicio, personaServicio, plantaServicio, scanner);

            // Manejo de las opciones del menú
            switch (opcion) {
                case 1:
                    System.out.println("\n=== Gestionar Ejemplares ===");
                    menuEjemplar.mostrarMenu();
                    break;
                case 2:
                    System.out.println("\n=== Gestionar Mensajes ===");
                    menuMensajes.mostrarMenuMensajes();
                    break;
                case 3:
                    System.out.println("\nCerrando sesión... Hasta luego.");
                    break;
                default:
                    System.out.println("Error: Opción no válida.");
            }

        } while (opcion != 3);
    }
}
