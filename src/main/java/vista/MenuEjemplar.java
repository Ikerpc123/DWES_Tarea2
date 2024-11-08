package vista;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import daoImpl.CredencialesDAOImpl;
import daoImpl.PersonaDAOImpl;
import daoImpl.PlantaDAOImpl;
import modelo.Credenciales;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;
import modelo.Planta;
import servicioImpl.CredencialServicioImpl;
import servicioImpl.PersonaServicioImpl;
import servicioImpl.PlantaServicioImpl;
import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.MensajeServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuEjemplar {

	PlantaServicio plantaServicio;
	EjemplarServicio ejemplarServicio;
	MensajeServicio mensajeServicio;
	PersonaServicio personaServicio;
	CredencialServicio credencialServicio;
	String usuario;
	
	public MenuEjemplar(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio, MensajeServicio mensajeServicio, CredencialServicio credencialServicio, PersonaServicio personaServicio, String usuario) {
		this.plantaServicio = plantaServicio;
		this.ejemplarServicio = ejemplarServicio;
		this.mensajeServicio = mensajeServicio;
		this.credencialServicio = credencialServicio;
		this.personaServicio = personaServicio;
		this.usuario = usuario;
    }
	
	public void mostrarMenu() {
    	
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
        	do {
	            System.out.println("\n--- Gestión Ejemplares ---");
	            System.out.println("1. Registrar nuevo ejemplar");
	            System.out.println("2. Filtrar por tipo de planta");
	            System.out.println("3. Ver mensajes de un ejemplar");
	            System.out.println("4. Volver");
	
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine(); // Consumir la nueva línea
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
	            	System.out.println("\nVolviendo...");
	                break;
	            default:
	                System.out.println("Opción no válida. Intente nuevamente.");
	        	}	
        	} while (opcion != 4);
        	
        }
		catch (InputMismatchException e) {
			System.out.print("\nInserte valores numéricos \n");
			mostrarMenu();
		}
        
    }

    private void registrarEjemplar(Scanner scanner) {
    	
    	Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    	String fechaFormateada = formatoFecha.format(fechaActual);
    	
    	try {
	            System.out.println("\n--- Registro de ejemplar ---");
	            System.out.println("Inserta el identificador de la planta: ");
	            String idPlanta = scanner.nextLine();
	            
	            Planta buscaPlanta = plantaServicio.findbyId(idPlanta);
	            if(buscaPlanta != null)
	            {
	            	Ejemplar nuevoEjemplar = new Ejemplar(null, null, idPlanta);
	            	ejemplarServicio.agregarEjemplar(nuevoEjemplar);
	            	Set<Ejemplar> todosEjemplares = ejemplarServicio.obtenerTodosEjemplares();
	            	
	            	List<Ejemplar> listaEjemplares = new ArrayList<>(todosEjemplares);
	            	Ejemplar ultimoEjemplar = listaEjemplares.get(0);
	            	ejemplarServicio.modificarEjemplar(ultimoEjemplar);
	            	
	            	Credenciales idPersona = credencialServicio.buscarPorUsuario(usuario);
	            	
	            	Mensaje mensaje = new Mensaje(	null, fechaActual, 
	            									"Autor: " +usuario+ " Fecha: " + fechaFormateada, 
	            									idPersona.getId(), 
	            									ultimoEjemplar.getId());
	            	
	            	mensajeServicio.insertarMensaje(mensaje);
	            	
	            }
	            else {
	            	 System.out.println("\nNo existe el identificador de la planta insertado");
	            }
	            
    	}
		catch (InputMismatchException e) {
			System.out.print("\nInserte valores permitidos \n");
			mostrarMenu();
		}
    }
    
    private void filtrarPlanta(Scanner scanner) {
        try {
            String input = "";
            Set<String> plantas = new HashSet<>();
            
            while (true) {
                System.out.print("Ingrese un código de planta (escriba 'fin' para terminar): ");
                input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("fin")) {
                    break;
                }
                
                plantas.add(input);
            }
            
            List<Planta> plantasSeleccionadas = new ArrayList<>();
            for (String codigoPlanta : plantas) {
                Planta planta = plantaServicio.findbyId(codigoPlanta);
                if (planta != null) {
                    plantasSeleccionadas.add(planta);
                } else {
                    System.out.println("No se encontró la planta con código: " + codigoPlanta);
                }
            }
            
            if (!plantasSeleccionadas.isEmpty()) {
                mostrarTablaEjemplares(plantasSeleccionadas);
            } else {
                System.out.println("No se seleccionaron plantas válidas.");
            }
            
        } catch (InputMismatchException e) {
            System.out.print("\nInserte valores permitidos\n");
            mostrarMenu();
        }
    }

    
    private  void mostrarTablaEjemplares(List<Planta> plantas) {
        System.out.printf("\nNombre Ejemplar      Planta          Nº de Mensajes            Fecha Último Mensaje");
        System.out.println("\n-------------------------------------------------------------------------------------------");

        for (Planta planta : plantas) {
        	
            // Obtener ejemplares de la planta seleccionada
            Set<Ejemplar> ejemplares = ejemplarServicio.obtenerTodosEjemplares();
            
            for (Ejemplar ejemplar : ejemplares) {
                if (ejemplar.getIdPlanta().equals(planta.getCodigo())) {
                	
                    // Contar mensajes asociados y obtener la fecha/hora del último mensaje
                    int numMensajes = contarMensajes(ejemplar);
                    
                    Date ultimaFechaMensaje = obtenerUltimaFechaMensaje(ejemplar);

                    // Mostrar la información en la tabla
                    System.out.printf("%-20s %-15s %-25d %s%n",
                            ejemplar.getNombre(),
                            planta.getNombreComun(),
                            numMensajes,
                            (ultimaFechaMensaje != null ? ultimaFechaMensaje.toString() : "Sin mensajes"));
                }
            }
        }
    }
    
    private int contarMensajes(Ejemplar ejemplar) {
        // Método que cuenta el número de mensajes para un ejemplar específico
        Set<Mensaje> mensajes = mensajeServicio.obtenerTodosMensajes();
        return (int) mensajes.stream().filter(m -> m.getEjemplar() == ejemplar.getId()).count();
    }
    
    private Date obtenerUltimaFechaMensaje(Ejemplar ejemplar) {
        // Método que obtiene la fecha del último mensaje para un ejemplar específico
        Set<Mensaje> mensajes = mensajeServicio.obtenerTodosMensajes();
        return mensajes.stream()
                .filter(m -> m.getEjemplar() == ejemplar.getId())
                .map(Mensaje::getFechaHora)
                .max(Date::compareTo)
                .orElse(null);
    }
    
    private void verMensajesDeSeguimiento(Scanner scanner) {
    	try {
            System.out.print("Ingrese el ID del ejemplar que desea consultar mensajes de seguimiento: ");
            long idEjemplar = scanner.nextLong();
            scanner.nextLine(); 

            
            Set<Mensaje> mensajes = mensajeServicio.obtenerMensajesPorEjemplar(idEjemplar);

            // Verificar si hay mensajes asociados
            if (mensajes.isEmpty()) {
                System.out.println("No hay mensajes de seguimiento para este ejemplar.");
                return;
            }

            // Mostrar los mensajes cronológicamente
            System.out.println("\nMensajes de seguimiento para el ejemplar con ID " + idEjemplar + ":");
            System.out.println("-------------------------------------------------");

            // Iterar sobre los mensajes y mostrar los detalles
            for (Mensaje mensaje : mensajes) {
                // Obtener el nombre de la persona que anotó el mensaje
                Persona persona = personaServicio.buscarPorId(mensaje.getPersona());
                String nombrePersona = (persona != null) ? persona.getNombre() : "Desconocido";

                // Mostrar detalles del mensaje
                System.out.println("Fecha/Hora: " + mensaje.getFechaHora());
                System.out.println("Mensaje: " + mensaje.getMensaje());
                System.out.println("Anotado por: " + nombrePersona);
                System.out.println("-------------------------------------------------");
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número válido para el ID.");
            scanner.nextLine(); // Limpiar el buffer de entrada
        } catch (Exception e) {
            System.out.println("Ocurrió un error al consultar los mensajes de seguimiento: " + e.getMessage());
        }
    }
}
