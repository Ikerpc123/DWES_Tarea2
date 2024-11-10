package vista;

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

/**
 * Clase que representa el menú de gestión de mensajes dentro de la aplicación. 
 * Permite a los usuarios anotar mensajes de seguimiento y filtrarlos por diferentes criterios.
 */
public class MenuMensaje {
    private final MensajeServicio mensajeServicio;
    private final PersonaServicio personaServicio;
    private final PlantaServicio plantaServicio;
    private final Scanner scanner;

    /**
     * Constructor de la clase {@link MenuMensaje}.
     * Inicializa los servicios necesarios para gestionar los mensajes, personas y plantas, 
     * y proporciona un scanner para la entrada del usuario.
     * 
     * @param mensajeServicio El servicio encargado de gestionar los mensajes.
     * @param personaServicio El servicio encargado de gestionar las personas.
     * @param plantaServicio El servicio encargado de gestionar las plantas.
     * @param scanner El objeto {@link Scanner} utilizado para leer la entrada del usuario.
     */
    public MenuMensaje(MensajeServicio mensajeServicio, PersonaServicio personaServicio, PlantaServicio plantaServicio, Scanner scanner) {
        this.mensajeServicio = mensajeServicio;
        this.personaServicio = personaServicio;
        this.plantaServicio = plantaServicio;
        this.scanner = scanner;
    }

    /**
     * Método que muestra el menú principal para la gestión de mensajes, permitiendo al usuario 
     * anotar un mensaje de seguimiento, mostrar mensajes filtrados o salir.
     */
    public void mostrarMenuMensajes() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Mensajes ---");
            System.out.println("1. Anotar mensaje de seguimiento");
            System.out.println("2. Mostrar mensajes filtrados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();

            // Procesar la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    anotarMensajeSeguimiento(); // Opción para anotar un mensaje
                    break;
                case 2:
                    mostrarMensajesFiltrados(); // Opción para mostrar mensajes filtrados
                    break;
                case 3:
                    System.out.println("Saliendo del menú de mensajes...");
                    break;
                default:
                    System.err.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    /**
     * Método para anotar un nuevo mensaje de seguimiento.
     * Permite ingresar el ID de un ejemplar, el ID de la persona que realiza la anotación 
     * y el contenido del mensaje. Luego, el mensaje es guardado en el sistema.
     */
    private void anotarMensajeSeguimiento() {
        LocalDateTime fechaActual = LocalDateTime.now();
        
        try {
            // Solicitar al usuario los datos necesarios para crear el mensaje
            System.out.print("Ingrese el ID del ejemplar a seguir: ");
            long idEjemplar = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Ingrese el ID de la persona que realiza la anotación: ");
            long idPersona = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Ingrese el mensaje de seguimiento: ");
            String contenidoMensaje = scanner.nextLine();

            // Crear el mensaje y guardarlo usando el servicio correspondiente
            Mensaje mensaje = new Mensaje(null, Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant()), contenidoMensaje, idPersona, idEjemplar);

            boolean resultado = mensajeServicio.insertarMensaje(mensaje);
            if (resultado) {
                System.out.println("Mensaje registrado exitosamente.");
            } else {
                System.err.println("Error al registrar el mensaje.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, ingrese datos correctos.");
            scanner.nextLine();
        }
    }

    /**
     * Método para mostrar los mensajes filtrados según diferentes criterios.
     * El usuario puede elegir filtrar los mensajes por persona, rango de fechas, 
     * tipo de planta o volver al menú anterior.
     */
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

            // Filtrar los mensajes según la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    filtrarPorPersona(); // Filtrar mensajes por persona
                    break;
                case 2:
                    filtrarPorRangoFechas(); // Filtrar mensajes por rango de fechas
                    break;
                case 3:
                    filtrarPorTipoPlanta(); // Filtrar mensajes por tipo de planta
                    break;
                case 4:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.err.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    /**
     * Filtra los mensajes por el ID de la persona que los realizó.
     */
    private void filtrarPorPersona() {
        System.out.print("Ingrese el ID de la persona: ");
        long idPersona = leerLong();
        Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorPersona(idPersona);
        mostrarMensajes(mensajes);
    }

    /**
     * Filtra los mensajes dentro de un rango de fechas dado por el usuario.
     */
    private void filtrarPorRangoFechas() {
        try {
            System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
            String fechaInicio = scanner.nextLine();
            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
            String fechaFin = scanner.nextLine();

            Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorRangoFechas(fechaInicio, fechaFin);
            mostrarMensajes(mensajes);
        } catch (Exception e) {
            System.err.println("Formato de fecha inválido.");
        }
    }

    /**
     * Filtra los mensajes por el código de la planta relacionada.
     */
    private void filtrarPorTipoPlanta() {
        System.out.print("Ingrese el código de la planta: ");
        String codigoPlanta = scanner.nextLine();
        Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorTipoPlanta(codigoPlanta);
        mostrarMensajes(mensajes);
    }

    /**
     * Muestra los mensajes filtrados por el usuario, con la información relevante 
     * como la fecha, el contenido y el nombre de la persona que realizó la anotación.
     * 
     * @param mensajes Conjunto de mensajes a mostrar.
     */
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

    /**
     * Lee una opción numérica de la entrada del usuario.
     * 
     * @return La opción seleccionada por el usuario.
     */
    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Ingrese un número.");
            scanner.nextLine();
            return -1;
        }
    }

    /**
     * Lee un valor numérico largo de la entrada del usuario.
     * 
     * @return El valor ingresado por el usuario.
     */
    private long leerLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Ingrese un número válido.");
            scanner.nextLine();
            return -1;
        }
    }
}
