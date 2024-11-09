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

public class MenuInicial {
    
    private PlantaServicio plantaServicio;
    private CredencialServicio credencialServicio;
    private PersonaServicio personaServicio;
    private EjemplarServicio ejemplarServicio;
    private MensajeServicio mensajeServicio;
    
    // Constructor con conexión a la base de datos
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

    // Constructor vacío
    public MenuInicial() {}

    public void mostrarMenuInicial() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        // Bucle para mostrar el menú inicial
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
                        System.out.println("Error: Seleccione una opción válida entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un número válido.");
                }
            }

            // Manejo de las opciones del menú
            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    MenuPlanta menuPlanta = new MenuPlanta();
                    menuPlanta.menuInvitado(scanner, plantaServicio);
                    break;
                case 3:
                    System.out.println("\nSaliendo del programa... Hasta luego.");
                    break;
                default:
                    System.out.println("Error: Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 3);

        scanner.close();
    }

    // Método para iniciar sesión
    private void iniciarSesion(Scanner scanner) {
        System.out.print("\nIngrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contra = scanner.nextLine();

        // Validación de credenciales
        if (credencialServicio.validar(usuario, contra)) {
            System.out.println("Inicio de sesión exitoso.");

            // Verificar si el usuario es administrador
            if (credencialServicio.esAdministrador(usuario, contra)) {
                accederComoAdministrador(usuario);
            } else {
                accederComoPersonal(usuario);
            }
        } else {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }

    // Método para acceder al menú de administrador
    private void accederComoAdministrador(String usuario) {
        MenuAdmin menuAdmin = new MenuAdmin(plantaServicio, personaServicio, credencialServicio, ejemplarServicio, mensajeServicio, usuario);
        menuAdmin.mostrarMenu();
    }

    // Método para acceder al menú de personal
    private void accederComoPersonal(String usuario) {
        MenuPersonal menuPersonal = new MenuPersonal(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
        menuPersonal.mostrarMenu();
    }
}
