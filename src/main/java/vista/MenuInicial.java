package vista;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import daoImpl.CredencialesDAOImpl;
import daoImpl.EjemplarDAOImpl;
import daoImpl.MensajeDAOImpl;
import daoImpl.PersonaDAOImpl;
import daoImpl.PlantaDAOImpl;
import servicioImpl.CredencialServicioImpl;
import servicioImpl.EjemplarServicioImpl;
import servicioImpl.MensajeServicioImpl;
import servicioImpl.PersonaServicioImpl;
import servicioImpl.PlantaServicioImpl;
import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

/**
 * Clase que representa el menú principal de la aplicación, donde el usuario puede iniciar sesión, 
 * entrar como invitado o salir del programa.
 * 
 * El menú permite gestionar las sesiones de usuarios mediante credenciales, y proporciona 
 * acceso a diferentes opciones según el tipo de usuario (administrador o personal).
 */
public class MenuInicial {
    
    private PlantaServicio plantaServicio;
    private CredencialServicio credencialServicio;
    private PersonaServicio personaServicio;
    private EjemplarServicio ejemplarServicio;
    private MensajeServicio mensajeServicio;

    /**
     * Constructor de la clase que inicializa los servicios para gestionar plantas, credenciales, 
     * personas, ejemplares y mensajes, mediante las implementaciones de DAO y conexión a la base de datos.
     * 
     * @param connection Conexión a la base de datos.
     */
    public MenuInicial(Connection connection) {
        PlantaDAOImpl plantaDAO = new PlantaDAOImpl(connection);
        CredencialesDAOImpl credenDAO = new CredencialesDAOImpl(connection);
        PersonaDAOImpl personaDAO = new PersonaDAOImpl(connection);
        EjemplarDAOImpl ejemplarDAO = new EjemplarDAOImpl(connection);
        MensajeDAOImpl mensajeDAO = new MensajeDAOImpl(connection);
        
        this.plantaServicio = new PlantaServicioImpl(plantaDAO);
        this.credencialServicio = new CredencialServicioImpl(credenDAO, personaDAO);
        this.personaServicio = new PersonaServicioImpl(personaDAO);
        this.ejemplarServicio = new EjemplarServicioImpl(ejemplarDAO);
        this.mensajeServicio = new MensajeServicioImpl(mensajeDAO);
    }

    /**
     * Constructor vacío para la clase {@link MenuInicial}.
     */
    public MenuInicial() {}

    /**
     * Método que muestra el menú inicial al usuario, permitiéndole seleccionar entre varias opciones:
     * 1. Iniciar sesión.
     * 2. Entrar como invitado.
     * 3. Salir del programa.
     */
    public void mostrarMenuInicial() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        // Bucle para mostrar el menú principal hasta que el usuario elija salir
        do {
            System.out.println("\n======== Menú Principal ========");
            System.out.println("  1. Iniciar Sesión");
            System.out.println("  2. Entrar como Invitado");
            System.out.println("  3. Salir");
            System.out.println("================================");

            // Validación de la entrada del usuario
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-3): ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion >= 1 && opcion <= 3) {
                        entradaValida = true;
                    } else {
                        System.err.println("Error: Seleccione una opción válida entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Ingrese un número válido.");
                }
            }

            // Manejo de las opciones seleccionadas por el usuario
            switch (opcion) {
                case 1:
                    iniciarSesion(scanner); // Iniciar sesión con credenciales
                    break;
                case 2:
                    // Entrar como invitado y acceder al menú de plantas
                    MenuPlanta menuPlanta = new MenuPlanta();
                    menuPlanta.menuInvitado(scanner, plantaServicio);
                    break;
                case 3:
                    System.out.println("\nSaliendo del programa... Hasta luego.");
                    break;
                default:
                    System.err.println("Error: Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 3);

        scanner.close();
    }

    /**
     * Método que permite al usuario iniciar sesión proporcionando su nombre de usuario y contraseña.
     * Si las credenciales son correctas, el sistema verifica si el usuario es administrador o personal.
     * 
     * @param scanner El objeto {@link Scanner} utilizado para leer la entrada del usuario.
     */
    private void iniciarSesion(Scanner scanner) {
        System.out.print("\nIngrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contra = scanner.nextLine();

        // Validación de las credenciales del usuario
        if (credencialServicio.validar(usuario, contra)) {
            System.out.println("Inicio de sesión exitoso.");

            // Verificación de si el usuario es administrador
            if (credencialServicio.esAdministrador(usuario, contra)) {
                accederComoAdministrador(usuario); // Acceder al menú de administrador
            } else {
                accederComoPersonal(usuario); // Acceder al menú de personal
            }
        } else {
            System.err.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }

    /**
     * Método que permite acceder al menú de administrador una vez que el usuario ha iniciado sesión
     * como administrador.
     * 
     * @param usuario El nombre de usuario del administrador.
     */
    private void accederComoAdministrador(String usuario) {
        // Crear el menú de administrador y mostrarlo
        MenuAdmin menuAdmin = new MenuAdmin(plantaServicio, personaServicio, credencialServicio, ejemplarServicio, mensajeServicio, usuario);
        menuAdmin.mostrarMenu();
    }

    /**
     * Método que permite acceder al menú de personal una vez que el usuario ha iniciado sesión
     * como un miembro del personal.
     * 
     * @param usuario El nombre de usuario del miembro del personal.
     */
    private void accederComoPersonal(String usuario) {
        // Crear el menú de personal y mostrarlo
        MenuPersonal menuPersonal = new MenuPersonal(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
        menuPersonal.mostrarMenu();
    }
}
