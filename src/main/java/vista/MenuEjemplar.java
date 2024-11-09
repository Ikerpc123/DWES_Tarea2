package vista;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import daoImpl.*;
import modelo.*;
import servicioImpl.*;
import servicios.*;

public class MenuEjemplar {

    // Servicios necesarios para la gestión
    PlantaServicio plantaServicio;
    EjemplarServicio ejemplarServicio;
    MensajeServicio mensajeServicio;
    PersonaServicio personaServicio;
    CredencialServicio credencialServicio;
    String usuario;

    // Constructor
    public MenuEjemplar(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio, 
                        MensajeServicio mensajeServicio, CredencialServicio credencialServicio, 
                        PersonaServicio personaServicio, String usuario) {
        this.plantaServicio = plantaServicio;
        this.ejemplarServicio = ejemplarServicio;
        this.mensajeServicio = mensajeServicio;
        this.credencialServicio = credencialServicio;
        this.personaServicio = personaServicio;
        this.usuario = usuario;
    }

    // Método para mostrar el menú principal
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======= Gestión Ejemplares =======");
            System.out.println("  1. Registrar nuevo ejemplar");
            System.out.println("  2. Filtrar por tipo de planta");
            System.out.println("  3. Ver mensajes de un ejemplar");
            System.out.println("  4. Volver");
            System.out.println("==================================");

            opcion = leerOpcion(scanner, 1, 4);

            switch (opcion) {
                case 1:
                    registrarEjemplar(scanner);
                    break;
                case 2:
                    filtrarPlanta(scanner);
                    break;
                case 3:
                    verMensajesDeSeguimiento(scanner);
                    break;
                case 4:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
            }
        } while (opcion != 4);
    }

    // Método para registrar un nuevo ejemplar
    private void registrarEjemplar(Scanner scanner) {
        Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);

        try {
            System.out.println("\n--- Registro de Ejemplar ---");
            System.out.print("Ingrese el identificador de la planta: ");
            String idPlanta = scanner.nextLine().trim();

            Planta planta = plantaServicio.findbyId(idPlanta);
            if (planta != null) {
                Ejemplar nuevoEjemplar = new Ejemplar(null, null, idPlanta);
                ejemplarServicio.agregarEjemplar(nuevoEjemplar);
                
                Set<Ejemplar> ejemplares = ejemplarServicio.obtenerTodosEjemplares();
                List<Ejemplar> listaEjemplares = new ArrayList<>(ejemplares);
                Ejemplar ultimoEjemplar = listaEjemplares.get(0);

                Credenciales credenciales = credencialServicio.buscarPorUsuario(usuario);
                Mensaje mensaje = new Mensaje(null, fechaActual, 
                                              "Autor: " + usuario + " Fecha: " + fechaFormateada, 
                                              credenciales.getId(), ultimoEjemplar.getId());
                
                mensajeServicio.insertarMensaje(mensaje);
                System.out.println("Ejemplar registrado exitosamente.");
            } else {
                System.out.println("No existe una planta con el identificador proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar el ejemplar: " + e.getMessage());
        }
    }

    // Método para filtrar ejemplares por tipo de planta
    private void filtrarPlanta(Scanner scanner) {
        try {
            Set<String> plantas = new HashSet<>();
            String input;

            while (true) {
                System.out.print("Ingrese un código de planta (escriba 'fin' para terminar): ");
                input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("fin")) {
                    break;
                }

                plantas.add(input);
            }

            List<Planta> plantasSeleccionadas = new ArrayList<>();
            for (String codigo : plantas) {
                Planta planta = plantaServicio.findbyId(codigo);
                if (planta != null) {
                    plantasSeleccionadas.add(planta);
                } else {
                    System.out.println("Planta con código '" + codigo + "' no encontrada.");
                }
            }

            if (!plantasSeleccionadas.isEmpty()) {
                mostrarTablaEjemplares(plantasSeleccionadas);
            } else {
                System.out.println("No se encontraron plantas válidas.");
            }
        } catch (Exception e) {
            System.out.println("Error al filtrar plantas: " + e.getMessage());
        }
    }

    // Método para mostrar una tabla de ejemplares
    private void mostrarTablaEjemplares(List<Planta> plantas) {
        System.out.printf("\n%-20s %-15s %-25s %s%n", 
                          "Nombre Ejemplar", "Planta", "Nº Mensajes", "Último Mensaje");
        System.out.println("-------------------------------------------------------------");

        for (Planta planta : plantas) {
            Set<Ejemplar> ejemplares = ejemplarServicio.obtenerTodosEjemplares();

            for (Ejemplar ejemplar : ejemplares) {
                if (ejemplar.getIdPlanta().equals(planta.getCodigo())) {
                    int numMensajes = contarMensajes(ejemplar);
                    Date ultimaFecha = obtenerUltimaFechaMensaje(ejemplar);
                    
                    System.out.printf("%-20s %-15s %-25d %s%n",
                                      ejemplar.getNombre(),
                                      planta.getNombreComun(),
                                      numMensajes,
                                      (ultimaFecha != null ? ultimaFecha.toString() : "Sin mensajes"));
                }
            }
        }
    }

    // Método para contar el número de mensajes de un ejemplar
    private int contarMensajes(Ejemplar ejemplar) {
        Set<Mensaje> mensajes = mensajeServicio.obtenerTodosMensajes();
        return (int) mensajes.stream().filter(m -> m.getEjemplar() == ejemplar.getId()).count();
    }

    // Método para obtener la última fecha de mensaje de un ejemplar
    private Date obtenerUltimaFechaMensaje(Ejemplar ejemplar) {
        Set<Mensaje> mensajes = mensajeServicio.obtenerTodosMensajes();
        return mensajes.stream()
                .filter(m -> m.getEjemplar() == ejemplar.getId())
                .map(Mensaje::getFechaHora)
                .max(Date::compareTo)
                .orElse(null);
    }

    // Método para ver mensajes de seguimiento
    private void verMensajesDeSeguimiento(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del ejemplar: ");
            long idEjemplar = scanner.nextLong();
            scanner.nextLine();

            Set<Mensaje> mensajes = mensajeServicio.obtenerMensajesPorEjemplar(idEjemplar);

            if (mensajes.isEmpty()) {
                System.out.println("No hay mensajes para este ejemplar.");
                return;
            }

            System.out.println("\nMensajes de seguimiento:");
            for (Mensaje mensaje : mensajes) {
                Persona persona = personaServicio.buscarPorId(mensaje.getPersona());
                String nombre = (persona != null) ? persona.getNombre() : "Desconocido";

                System.out.println("Fecha: " + mensaje.getFechaHora());
                System.out.println("Mensaje: " + mensaje.getMensaje());
                System.out.println("Autor: " + nombre);
                System.out.println("------------------------------");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Ingrese un ID válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error al consultar mensajes: " + e.getMessage());
        }
    }

    // Método auxiliar para leer opciones numéricas
    private int leerOpcion(Scanner scanner, int min, int max) {
        int opcion;
        while (true) {
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.println("Opción fuera de rango. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número.");
            }
        }
    }
}
