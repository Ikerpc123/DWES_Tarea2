package vista;

import java.util.InputMismatchException;
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
	
	public MenuAdmin() {}

    public MenuAdmin(PlantaServicio plantaServicio, PersonaServicio personaServicio, CredencialServicio credencialServicio, 
    		EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.personaServicio = personaServicio;
        this.credencialServicio = credencialServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.usuario = usuario;
    }
    
	public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======= Menú Administrador =======");
            System.out.println("  1. Gestionar plantas");
            System.out.println("  2. Gestionar ejemplares");
            System.out.println("  3. Gestionar mensajes");
            System.out.println("  4. Registrar persona");
            System.out.println("  5. Cerrar Sesión");
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
                        System.out.println("Error: Seleccione una opción válida entre 1 y 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un número válido.");
                }
            }

            // Instancias de los submenús
            MenuPlanta menuPlanta = new MenuPlanta();
            MenuRegistro menuRegistro = new MenuRegistro(personaServicio, credencialServicio);
            MenuEjemplar menuEjemplar = new MenuEjemplar(plantaServicio, ejemplarServicio, mensajeServicio, credencialServicio, personaServicio, usuario);
            MenuMensaje menuMensajes = new MenuMensaje(mensajeServicio, personaServicio, plantaServicio, scanner);

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
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Error: Opción no válida.");
                    break;
            }

        } while (opcion != 5);

        System.out.println("Sesión finalizada. Hasta luego.");
    }
}
