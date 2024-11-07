package vista;

import java.util.Scanner;

import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuPersonal {
	PlantaServicio plantaServicio;
	EjemplarServicio ejemplarServicio;
	MensajeServicio mensajeServicio;
	CredencialServicio credencialServicio;
	String usuario;
	
	public MenuPersonal() {
		
	}

    public MenuPersonal(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, CredencialServicio credencialServicio, String usuario) {
    	this.plantaServicio = plantaServicio;
		this.ejemplarServicio = ejemplarServicio;
		this.mensajeServicio = mensajeServicio;
		this.credencialServicio = credencialServicio;
		this.usuario = usuario;
    }
    
	public void mostrarMenu() {
		
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n--- Menú Personal ---");
            System.out.println("1. Gestionar ejemplares");
            System.out.println("2. Gestionar mensajes");
            System.out.println("3. Cerrar Sesión");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, usuario);
            
            switch (opcion) {
                case 1:
                	menuEjemplar.mostrarMenu();
                    break;
                case 2:
                	
                    break;
                case 3:
                	
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);

    }
}
