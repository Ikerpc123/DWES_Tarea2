package vista;

import java.util.Scanner;
import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

/**
 * Clase que representa el menú de opciones del personal autenticado en la aplicación. 
 * Permite al usuario gestionar ejemplares, gestionar mensajes o cerrar sesión.
 */
public class MenuPersonal {
    
    private PlantaServicio plantaServicio;
    private EjemplarServicio ejemplarServicio;
    private MensajeServicio mensajeServicio;
    private CredencialServicio credencialServicio;
    private PersonaServicio personaServicio;
    private String usuario;

    /**
     * Constructor vacío de la clase {@link MenuPersonal}.
     * Este constructor se utiliza cuando se desea crear un objeto sin inicializar los servicios y el usuario.
     */
    public MenuPersonal() {}

    /**
     * Constructor de la clase {@link MenuPersonal}.
     * Inicializa los servicios necesarios para gestionar ejemplares, mensajes, credenciales, personas y plantas, 
     * así como el nombre de usuario del personal autenticado.
     * 
     * @param plantaServicio El servicio encargado de gestionar las plantas.
     * @param ejemplarServicio El servicio encargado de gestionar los ejemplares.
     * @param mensajeServicio El servicio encargado de gestionar los mensajes.
     * @param credencialServicio El servicio encargado de gestionar las credenciales.
     * @param personaServicio El servicio encargado de gestionar las personas.
     * @param usuario El nombre de usuario del personal autenticado.
     */
    public MenuPersonal(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, 
                        CredencialServicio credencialServicio, PersonaServicio personaServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.credencialServicio = credencialServicio;
        this.personaServicio = personaServicio;
        this.usuario = usuario;
    }

    /**
     * Método que muestra el menú principal para el personal autenticado en la aplicación.
     * El menú permite al usuario gestionar ejemplares, gestionar mensajes o cerrar sesión.
     */
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
                        entradaValida = true; // Opción válida, salimos del bucle
                    } else {
                        System.err.println("Error: Seleccione una opción válida entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Ingrese un número válido.");
                }
            }

            // Creación de los menús de subgestión
            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
            MenuMensaje menuMensajes = new MenuMensaje(mensajeServicio, personaServicio, plantaServicio, scanner);

            // Manejo de las opciones del menú
            switch (opcion) {
                case 1:
                    // Si selecciona la opción 1, se gestiona ejemplares
                    System.out.println("\n=== Gestionar Ejemplares ===");
                    menuEjemplar.mostrarMenu();
                    break;
                case 2:
                    // Si selecciona la opción 2, se gestionan los mensajes
                    System.out.println("\n=== Gestionar Mensajes ===");
                    menuMensajes.mostrarMenuMensajes();
                    break;
                case 3:
                    // Si selecciona la opción 3, se cierra la sesión
                    System.err.println("\nCerrando sesión... Hasta luego.");
                    break;
                default:
                    System.err.println("Error: Opción no válida.");
            }

        } while (opcion != 3); // El ciclo se repite hasta que el usuario elija la opción 3 para cerrar sesión
    }
}
