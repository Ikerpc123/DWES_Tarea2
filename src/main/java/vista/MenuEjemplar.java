package vista;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import daoImpl.CredencialesDAOImpl;
import daoImpl.PersonaDAOImpl;
import daoImpl.PlantaDAOImpl;
import modelo.Ejemplar;
import modelo.Planta;
import servicioImpl.CredencialServicioImpl;
import servicioImpl.PersonaServicioImpl;
import servicioImpl.PlantaServicioImpl;
import servicios.EjemplarServicio;
import servicios.PlantaServicio;

public class MenuEjemplar {

	PlantaServicio plantaServicio;
	EjemplarServicio ejemplarServicio;
	
	public MenuEjemplar(PlantaServicio plantaServicio, EjemplarServicio ejemplarServicio) {
		this.plantaServicio = plantaServicio;
		this.ejemplarServicio = ejemplarServicio;
    }
	
	public void mostrarMenu() {
    	
        Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
        	do {
	            System.out.println("\n--- Menú Ejemplar ---");
	            System.out.println("1. Registrar nuevo ejemplar");
	            System.out.println("2. Volver");
	            System.out.println("3. Cerrar Sesión");
	
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine(); // Consumir la nueva línea
	        	switch (opcion) {
	            case 1:
	            	registrarEjemplar(scanner);
	                break;
	            case 2:
	            	System.out.println("\nVolviendo...");
	                break;
	            case 3:
	                System.out.println("\nSe ha cerrado sesión...");
	                MenuInicial menuInicial = new MenuInicial();
	                menuInicial.mostrarMenuInicial();
	                break;
	            default:
	                System.out.println("Opción no válida. Intente nuevamente.");
	        	}	
        	} while (opcion != 2);
        	
        }
		catch (InputMismatchException e) {
			System.out.print("\nInserte valores numéricos \n");
			mostrarMenu();
		}
        
    }

    private void registrarEjemplar(Scanner scanner) {
    	
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
	            	
	            	Ejemplar ultimoEjemplar = listaEjemplares.get(todosEjemplares.size() - 1);
	            	
	            	ejemplarServicio.modificarEjemplar(ultimoEjemplar);
	            	
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
}
