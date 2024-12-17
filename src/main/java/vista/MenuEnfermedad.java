package vista;

import java.util.List;
import java.util.Scanner;

import modelo.Enfermedad;
import modelo.Parasito;
import modelo.Planta;
import servicios.CredencialServicio;
import servicios.EjemplarServicio;
import servicios.EnfermedadServicio;
import servicios.MensajeServicio;
import servicios.ParasitoServicio;
import servicios.PersonaServicio;
import servicios.PlantaServicio;

public class MenuEnfermedad {
	
	private PlantaServicio plantaServicio;
	private EnfermedadServicio enfermedadServicio;
	private ParasitoServicio parasitoServicio;

	public MenuEnfermedad(PlantaServicio plantaServicio, EnfermedadServicio enfermedadServicio, ParasitoServicio parasitoServicio) {
			this.plantaServicio = plantaServicio;
			this.enfermedadServicio = enfermedadServicio;
			this.parasitoServicio = parasitoServicio;
	}
	
	 public void mostrarMenu() {
	        Scanner scanner = new Scanner(System.in);
	        int opcion = -1;

	        do {
	            System.out.println("\n======= Menú Enfermedades =======");
	            System.out.println("  1. Registrar Enfermedad");
	            System.out.println("  2. Registrar Parásito");
	            System.out.println("  3. Modificar Parásitos");
	            System.out.println("  4. Volver");
	            System.out.println("==================================");

	            // Validación de entrada del usuario
	            boolean entradaValida = false;
	            while (!entradaValida) {
	                try {
	                    System.out.print("Seleccione una opción (1-5): ");
	                    opcion = Integer.parseInt(scanner.nextLine());
	                    if (opcion >= 1 && opcion <= 5) {
	                        entradaValida = true;
	                    } else {
	                        System.err.println("Error: Seleccione una opción válida entre 1 y 5.");
	                    }
	                } catch (NumberFormatException e) {
	                    System.err.println("Error: Ingrese un número válido.");
	                }
	            }
	            

	            // Manejo de las opciones
	            switch (opcion) {
	                case 1:
	                	registrarEnfermedad();
	                    break;
	                case 2:
	                	registrarParasito();
	                    break;
	                case 3:
	                	System.err.println("No me ha dado tiempo");
	                    break;
	                case 4:
	                    System.err.println("Volviendo...");
	                    break;
	                default:
	                    System.err.println("Error: Opción no válida.");
	                    break;
	            }

	        } while (opcion != 5);

	        System.err.println("Sesión finalizada. Hasta luego.");
	    }
	 
	 
	 public void registrarEnfermedad() {
		    // Se le pide al usuario que ingrese los datos de la enfermedad
		    Scanner scanner = new Scanner(System.in);

		    System.out.println("\nA qué planta pertenece esta enfermedad?");
		    String codPlanta = scanner.nextLine().toUpperCase();

		    Planta planta = plantaServicio.findbyId(codPlanta);
		    if (planta != null) {
		        System.out.println("Ingrese el nombre de la enfermedad:");
		        String nombre = scanner.nextLine();
		        System.out.println("Ingrese los síntomas de la enfermedad:");
		        String sintomas = scanner.nextLine();
		        System.out.println("¿Es nociva la enfermedad? [Y/N]");
		        String nociva = scanner.nextLine().toUpperCase();
		        System.out.println("¿Es provocada por algún parásito? [Y/N]");
		        String parasito = scanner.nextLine().toUpperCase();

		        boolean esNociva = nociva.equals("Y");
		        boolean hayParasito = parasito.equals("Y");

		        Enfermedad enfermedad = new Enfermedad(nombre, sintomas, esNociva, codPlanta);
		        enfermedadServicio.agregar(enfermedad);

		        if (hayParasito) {
		            System.out.println("\nSeleccione el ID de los parásitos que provocan esta enfermedad (separados por comas):");
		            mostrarParasitos();

		            String[] seleccionados = scanner.nextLine().split(",");
		            for (String seleccionado : seleccionados) {
		                try {
		                	Enfermedad nuevaEnfermedad = enfermedadServicio.findByNombre(nombre);
		                    Long id = Long.parseLong(seleccionado.trim());
		                    Parasito parasitoSeleccionado = parasitoServicio.findById(id);
		                    if (parasitoSeleccionado != null) {
		                        parasitoSeleccionado.setIdEnfermedad(nuevaEnfermedad.getId());
		                        parasitoServicio.update(parasitoSeleccionado, nuevaEnfermedad.getId());
		                    } else {
		                        System.err.println("Parásito con ID " + id + " no encontrado.");
		                    }
		                } catch (NumberFormatException e) {
		                    System.err.println("ID de parásito inválido: " + seleccionado);
		                }
		            }
		        }
		    } else {
		        System.err.println("La planta insertada no existe");
		    }
		}
		
		public void mostrarParasitos() {
		    System.out.println("\n======= Lista de Parásitos =======");
		    List<Parasito> parasitos = parasitoServicio.mostrar();

		    if (parasitos.isEmpty()) {
		        System.out.println("No hay parásitos registrados.");
		    } else {
		        System.out.printf("%-20s %-20s %-20s\n","ID", "Nombre", "Color");
		        System.out.println("========================================================================");
		        for (Parasito parasito : parasitos) {
		            System.out.printf("%-20s %-20s %-20s\n", parasito.getId(), parasito.getNombre(), parasito.getColor());
		        }
		    }
		    System.out.println("-------------------------------------------------------------------------------");
		}
		
		
		public void registrarParasito() {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Ingrese el nombre del parásito:");
			String nombre = scanner.nextLine();
			System.out.println("Ingrese el color del parásito:");
			String color = scanner.nextLine();

			parasitoServicio.insertar(new Parasito(nombre, color));

		}

}
