package vista;

import java.util.Scanner;

import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuAdmin {
	
	private PlantaServicio plantaServicio;
	private PersonaServicio personaServicio;
	private CredencialServicio credencialServicio;
	private EjemplarServicio ejemplarServicio;
	private MensajeServicio mensajeServicio;
	private String usuario;
	
	public MenuAdmin() {
		
	}

    public MenuAdmin(PlantaServicio plantaServicio, PersonaServicio personaServicio, CredencialServicio credencialServicio, EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.personaServicio = personaServicio;
        this.credencialServicio = credencialServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.usuario = usuario;
    }
    
	public void mostrarMenu() {
		
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Gestionar plantas");
            System.out.println("2. Gestionar ejemplares");
            System.out.println("3. Gestionar mensajes");
            System.out.println("4. Registrar persona");
            System.out.println("5. Cerrar Sesión");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            MenuPlanta menuPlanta = new MenuPlanta();
            MenuRegistro menuRegistro = new MenuRegistro(personaServicio, credencialServicio);
            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, usuario);
            
            switch (opcion) {
                case 1:
                	menuPlanta.gestionarPlanta(plantaServicio);
                    break;
                case 2:
                	menuEjemplar.mostrarMenu();
                    break;
                case 3:
                	
                    break;
                case 4:
                	menuRegistro.mostrarMenuRegistro();
                    break;
                case 5:
                    System.out.println("Saliendo del menú administrador...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }
}
