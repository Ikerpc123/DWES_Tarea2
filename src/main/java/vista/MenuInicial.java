package vista;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.CredencialesDAO;
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

    public MenuInicial() {
		
	}

	public void mostrarMenuInicial() {
    	
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
        	do {
	            System.out.println("\n--- Menú Principal ---");
	            System.out.println("1. Iniciar Sesión");
	            System.out.println("2. Entrar como Invitado");
	            System.out.println("3. Salir");
	
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine();
	        	switch (opcion) {
	            case 1:
	                iniciarSesion(scanner);
	                break;
	            case 2:
	            	MenuPlanta menuPlanta = new MenuPlanta();
	            	menuPlanta.verPlantas(plantaServicio);
	                break;
	            case 3:
	                System.out.println("\nSaliendo del programa...");
	                break;
	            default:
	                System.out.println("Opción no válida. Intente nuevamente.");
	        	}	
        	} while (opcion != 3);
        	
        	scanner.close();
        }
		catch (InputMismatchException e) {
			System.out.print("\nInserte valores numéricos \n");
			mostrarMenuInicial();
		}
        
    }

    private void iniciarSesion(Scanner scanner) {
    	System.out.print("\nIngrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contra = scanner.nextLine();

        if (credencialServicio.validar(usuario, contra)) {
            System.out.println("Inicio de sesión exitoso.");
            
            // Verificar si el usuario es administrador
            if (credencialServicio.esAdministrador(usuario, contra)) {
                accederComoAdministrador(usuario);  // Iniciar el menú de administrador
            } else {
            	accederComoPersonal(usuario);  // Iniciar el menú regular de usuario
            }
            
        } else {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }

    private void accederComoAdministrador(String usuario) {
    	MenuAdmin menuAdmin = new MenuAdmin(plantaServicio, personaServicio, credencialServicio, ejemplarServicio, mensajeServicio, usuario);
    	menuAdmin.mostrarMenu();
    }
    
    private void accederComoPersonal(String usuario)
    { 
    	MenuPersonal menuPersonal = new MenuPersonal(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, usuario);
    	menuPersonal.mostrarMenu();
    }
}
