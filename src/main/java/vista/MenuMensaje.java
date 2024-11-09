package vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    anotarMensajeSeguimiento();
                    break;
                case 2:
                    mostrarMensajesFiltrados();
                    break;
                case 3:
                    System.out.println("Saliendo del menú de mensajes...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    // Método para anotar un nuevo mensaje
    private void anotarMensajeSeguimiento() {
        LocalDateTime fechaActual = LocalDateTime.now();
        
        try {
            System.out.print("Ingrese el ID del ejemplar a seguir: ");
            long idEjemplar = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Ingrese el ID de la persona que realiza la anotación: ");
            long idPersona = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Ingrese el mensaje de seguimiento: ");
            String contenidoMensaje = scanner.nextLine();

            Mensaje mensaje = new Mensaje(null, Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant()), contenidoMensaje, idPersona, idEjemplar);

            boolean resultado = mensajeServicio.insertarMensaje(mensaje);
            if (resultado) {
                System.out.println("Mensaje registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el mensaje.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese datos correctos.");
            scanner.nextLine();
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
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    filtrarPorPersona();
                    break;
                case 2:
                    filtrarPorRangoFechas();
                    break;
                case 3:
                    filtrarPorTipoPlanta();
                    break;
                case 4:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    // Filtrar mensajes por persona
    private void filtrarPorPersona() {
        System.out.print("Ingrese el ID de la persona: ");
        long idPersona = leerLong();
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
                System.out.printf("Fecha: %s | Mensaje: %s | Persona: %s%n",
                        mensaje.getFechaHora(), mensaje.getMensaje(), nombrePersona);
            }
        }
    }

    // Métodos auxiliares para manejo de entradas
    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Ingrese un número.");
            scanner.nextLine();
            return -1;
        }
    }

    private long leerLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Ingrese un número válido.");
            scanner.nextLine();
            return -1;
        }
    }
}
