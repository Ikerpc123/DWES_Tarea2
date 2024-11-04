package vista;

import java.util.Scanner;

import modelo.Credenciales;
import modelo.Persona;
import servicioImpl.PersonaServicioImpl;
import servicios.CredencialServicio;
import servicios.PersonaServicio;

public class MenuRegistro {
	 private final PersonaServicio personaServicio;
	 private final CredencialServicio credencialServicio;

    public MenuRegistro(PersonaServicio personaServicio, CredencialServicio credencialServicio) {
        this.personaServicio = personaServicio;
        this.credencialServicio = credencialServicio;
    }

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

        // Intento de registro
        if (personaServicio.registrarPersona(nuevaPersona)) {
            System.out.println("Registro exitoso. La persona ha sido añadida al sistema.");
        } else {
            System.out.println("El registro ha fallado. Verifique los datos e intente nuevamente.");
        }
        if (credencialServicio.insertarCredenciales(nuevasCredenciales, email))
        {
            System.out.println("Registro exitoso. La persona ha sido añadida al sistema.");
        } else {
            System.out.println("El registro ha fallado. Verifique los datos e intente nuevamente.");
        }

    }
}
