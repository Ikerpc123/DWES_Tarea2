package vista;

import java.sql.Connection;
import java.util.Scanner;

import dao.CredencialesDAO;
import daoImpl.CredencialesDAOImpl;
import daoImpl.PlantaDAOImpl;
import servicioImpl.CredencialServicioImpl;
import servicioImpl.PlantaServicioImpl;
import servicios.CredencialServicio;
import servicios.PlantaServicio;

public class MenuInicial {
	private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    
    private PlantaServicio plantaServicio;
    private CredencialServicio credencialServicio;
    
    public MenuInicial(Connection connection) {
        PlantaDAOImpl plantaDAO = new PlantaDAOImpl(connection);
        CredencialesDAOImpl credenDAO = new CredencialesDAOImpl(connection);
        
        this.plantaServicio = new PlantaServicioImpl(plantaDAO);
        this.credencialServicio = new CredencialServicioImpl(credenDAO);
    }

    public void mostrarMenuInicial() {
    	
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Entrar como Invitado");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    registrarse(scanner);
                    break;
                case 2:
                    iniciarSesion(scanner);
                    break;
                case 3:
                	MenuPlanta menuPlanta = new MenuPlanta();
                	menuPlanta.verPlantas(plantaServicio);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private void registrarse(Scanner scanner) {
    }

    private void iniciarSesion(Scanner scanner) {
    	System.out.print("\nIngrese el nombre de usuario: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("Inicio de sesión exitoso como administrador.");
            accederComoAdministrador();
        } else {
            if (credencialServicio.validar(username, password)) {
                System.out.println("Inicio de sesión exitoso.");
                
                // Aquí podrías redirigir al menú o funcionalidades de usuario común
                
            } else {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
            }
        }
    }

    private void accederComoAdministrador() {
    	MenuAdmin menuAdmin = new MenuAdmin();
    	menuAdmin.mostrarMenu();
    }
}
