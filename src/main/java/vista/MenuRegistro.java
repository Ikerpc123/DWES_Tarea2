package vista;

import java.util.Scanner;
import modelo.Credenciales;
import modelo.Persona;
import servicios.CredencialServicio;
import servicios.PersonaServicio;

/**
 * Clase que representa el menú de registro de una nueva persona y sus credenciales.
 * Permite capturar los datos del usuario e insertarlos en el sistema.
 */
public class MenuRegistro {

    // Servicios para gestionar personas y credenciales
    private final PersonaServicio personaServicio;
    private final CredencialServicio credencialServicio;

    /**
     * Constructor de la clase MenuRegistro.
     * 
     * @param personaServicio Servicio para gestionar las personas.
     * @param credencialServicio Servicio para gestionar las credenciales.
     */
    public MenuRegistro(PersonaServicio personaServicio, CredencialServicio credencialServicio) {
        this.personaServicio = personaServicio;
        this.credencialServicio = credencialServicio;
    }

    /**
     * Método para mostrar el menú de registro y capturar los datos del nuevo usuario.
     * Registra una nueva persona y sus credenciales en el sistema.
     */
    public void mostrarMenuRegistro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Registro de Nueva Persona ---");

        // Captura de datos del usuario
        System.out.print("Ingrese el nombre real de la persona: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el email de la persona: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        // Creación de instancias de Persona y Credenciales
        Persona nuevaPersona = new Persona(nombre, email);
        Credenciales nuevasCredenciales = new Credenciales(nombreUsuario, password);

        // Intento de registro de la persona
        if (personaServicio.registrarPersona(nuevaPersona)) {
            System.out.println("Registro exitoso. La persona ha sido añadida al sistema.");
        } else {
            System.err.println("El registro ha fallado. Verifique los datos e intente nuevamente.");
        }

        // Intento de registro de las credenciales
        if (credencialServicio.insertarCredenciales(nuevasCredenciales, email)) {
            System.out.println("Registro exitoso. Las credenciales han sido añadidas al sistema.");
        } else {
            System.err.println("El registro de credenciales ha fallado. Verifique los datos e intente nuevamente.");
        }
    }
}
