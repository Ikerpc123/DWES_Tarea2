package vista;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import modelo.Mensaje;
import modelo.Persona;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuMensaje {
	private final MensajeServicio mensajeServicio;
    private final PersonaServicio personaServicio;
    private final PlantaServicio plantaServicio;
    private final Scanner scanner;

    public MenuMensaje(MensajeServicio mensajeServicio, PersonaServicio personaServicio, PlantaServicio plantaServicio, Scanner scanner) {
        this.mensajeServicio = mensajeServicio;
        this.personaServicio = personaServicio;
        this.plantaServicio = plantaServicio;
        this.scanner = scanner;
    }

    // Menú principal de gestión de mensajes
    public void mostrarMenuMensajes() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Mensajes ---");
            System.out.println("1. Anotar mensaje de seguimiento");
            System.out.println("2. Mostrar mensajes filtrados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	anotarMensajeSeguimiento();
                case 2:
                	mostrarMensajesFiltrados();
                case 3:
                	System.out.println("Saliendo del menú de mensajes...");
                default:
                	System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    // Método para anotar un nuevo mensaje
    private void anotarMensajeSeguimiento() {
    	Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    	String fechaFormateada = formatoFecha.format(fechaActual);
    	
        try {
            System.out.print("Ingrese el ID del ejemplar a seguir: ");
            long idEjemplar = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese el ID de la persona que realiza la anotación: ");
            long idPersona = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese el mensaje de seguimiento: ");
            String contenidoMensaje = scanner.nextLine();
            
            Mensaje mensaje = new Mensaje(null, fechaActual, contenidoMensaje, idPersona, idEjemplar);

            // Registrar el mensaje de seguimiento
            boolean resultado = mensajeServicio.insertarMensaje(mensaje);

            if (resultado) {
                System.out.println("Mensaje registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el mensaje.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese datos correctos.");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    // Método para mostrar mensajes filtrados
    private void mostrarMensajesFiltrados() {
        int opcion;
        do {
            System.out.println("\n--- Filtrar Mensajes ---");
            System.out.println("1. Filtrar por persona");
            System.out.println("2. Filtrar por rango de fechas");
            System.out.println("3. Filtrar por tipo de planta");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                	filtrarPorPersona();
                case 2:
                	filtrarPorRangoFechas();
                case 3:
                	filtrarPorTipoPlanta();
                case 4:
                	System.out.println("Volviendo al menú anterior...");
                default: 
                	System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    // Filtrar mensajes por persona
    private void filtrarPorPersona() {
        System.out.print("Ingrese el ID de la persona: ");
        long idPersona = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorPersona(idPersona);
        mostrarMensajes(mensajes);
    }

    // Filtrar mensajes por rango de fechas
    private void filtrarPorRangoFechas() {
        try {
            System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
            String fechaInicio = scanner.nextLine();
            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
            String fechaFin = scanner.nextLine();

            Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorRangoFechas(fechaInicio, fechaFin);
            mostrarMensajes(mensajes);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido.");
        }
    }

    // Filtrar mensajes por tipo de planta
    private void filtrarPorTipoPlanta() {
        System.out.print("Ingrese el código de la planta: ");
        String codigoPlanta = scanner.nextLine();

        Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorTipoPlanta(codigoPlanta);
        mostrarMensajes(mensajes);
    }

    // Mostrar los mensajes
    private void mostrarMensajes(Set<Mensaje> mensajes) {
        if (mensajes.isEmpty()) {
            System.out.println("No se encontraron mensajes.");
        } else {
            System.out.println("\n--- Mensajes de seguimiento ---");
            for (Mensaje mensaje : mensajes) {
                Persona persona = personaServicio.buscarPorId(mensaje.getPersona());
                String nombrePersona = (persona != null) ? persona.getNombre() : "Desconocido";
                System.out.printf("Fecha: %s | Mensaje: %s | Persona: %s\n",
                        mensaje.getFechaHora(), mensaje.getMensaje(), nombrePersona);
            }
        }
    }
}
