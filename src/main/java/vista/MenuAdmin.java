package vista;

import java.util.Scanner;

import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.EnfermedadServicio;
import servicios.MensajeServicio;
import servicios.ParasitoServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

/**
 * Clase que representa el menú principal del administrador en la interfaz de usuario.
 * 
 * El {@code MenuAdmin} proporciona las opciones para gestionar diferentes entidades del sistema,
 * tales como plantas, ejemplares, mensajes y personas. Permite al administrador interactuar con
 * las funcionalidades del sistema mediante un menú interactivo en consola.
 * 
 * Los administradores pueden seleccionar opciones para gestionar las plantas, ejemplares, mensajes
 * o registrar personas, y también tienen la opción de cerrar sesión.
 * 
 * Esta clase maneja la entrada del usuario mediante el uso de un objeto {@link Scanner} y valida
 * que las opciones ingresadas sean correctas, mostrando mensajes de error en caso contrario.
 */
public class MenuAdmin {
    
    private PlantaServicio plantaServicio;
    private PersonaServicio personaServicio;
    private CredencialServicio credencialServicio;
    private EjemplarServicio ejemplarServicio;
    private MensajeServicio mensajeServicio;
    private EnfermedadServicio enfermedadServicio;
    private ParasitoServicio parasitoServicio;
    private String usuario;
    
    /**
     * Constructor por defecto.
     */
    public MenuAdmin() {}

    /**
     * Constructor que inicializa los servicios necesarios para la gestión de las entidades del sistema
     * y establece el usuario del administrador.
     * 
     * @param plantaServicio El servicio para gestionar las plantas.
     * @param personaServicio El servicio para gestionar las personas.
     * @param credencialServicio El servicio para gestionar las credenciales.
     * @param ejemplarServicio El servicio para gestionar los ejemplares.
     * @param mensajeServicio El servicio para gestionar los mensajes.
     * @param usuario El usuario que ha iniciado sesión.
     */
    public MenuAdmin(PlantaServicio plantaServicio, PersonaServicio personaServicio, CredencialServicio credencialServicio, 
                     EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, EnfermedadServicio enfermedadServicio, ParasitoServicio parasitoServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.personaServicio = personaServicio;
        this.credencialServicio = credencialServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.enfermedadServicio = enfermedadServicio;
        this.parasitoServicio = parasitoServicio;
        this.usuario = usuario;
    }

    /**
     * Método que muestra el menú principal para el administrador y gestiona la interacción con el usuario.
     * El menú ofrece opciones para gestionar plantas, ejemplares, mensajes, registrar personas o cerrar sesión.
     * 
     * El método valida la entrada del usuario y asegura que se seleccione una opción válida entre las disponibles.
     * También instancia y delega las acciones correspondientes a submenús específicos para cada tipo de gestión.
     */
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======= Menú Administrador =======");
            System.out.println("  1. Gestionar plantas");
            System.out.println("  2. Gestionar ejemplares");
            System.out.println("  3. Gestionar mensajes");
            System.out.println("  4. Registrar persona");
            System.out.println("  5. Gestionar enfermedades");
            System.out.println("  6. Cerrar Sesión");
            System.out.println("==================================");

            // Validación de entrada del usuario
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-5): ");
                    opcion = Integer.parseInt(scanner.nextLine());  // Usamos `nextLine` y parseamos para evitar problemas de salto de línea
                    if (opcion >= 1 && opcion <= 5) {
                        entradaValida = true;
                    } else {
                        System.err.println("Error: Seleccione una opción válida entre 1 y 5.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Ingrese un número válido.");
                }
            }

            // Instancias de los submenús
            MenuPlanta menuPlanta = new MenuPlanta();
            MenuRegistro menuRegistro = new MenuRegistro(personaServicio, credencialServicio);
            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
            MenuMensaje menuMensajes = new MenuMensaje(mensajeServicio, personaServicio, plantaServicio, scanner);
            MenuEnfermedad menuEnfermedad = new MenuEnfermedad(plantaServicio, enfermedadServicio, parasitoServicio);
            

            // Manejo de las opciones
            switch (opcion) {
                case 1:
                    System.out.println("\n--- Gestión de Plantas ---");
                    menuPlanta.gestionarPlanta(plantaServicio);
                    break;
                case 2:
                    System.out.println("\n--- Gestión de Ejemplares ---");
                    menuEjemplar.mostrarMenu();
                    break;
                case 3:
                    System.out.println("\n--- Gestión de Mensajes ---");
                    menuMensajes.mostrarMenuMensajes();
                    break;
                case 4:
                    System.out.println("\n--- Registro de Persona ---");
                    menuRegistro.mostrarMenuRegistro();
                    break;
                case 5:
                    menuEnfermedad.mostrarMenu();
                    break;
                case 6:
                    System.err.println("Cerrando sesión...");
                    break;
                default:
                    System.err.println("Error: Opción no válida.");
                    break;
            }

        } while (opcion != 5);

        System.err.println("Sesión finalizada. Hasta luego.");
    }
}
