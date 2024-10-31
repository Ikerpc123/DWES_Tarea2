package vista;

import java.util.Scanner;
import java.util.Set;

import modelo.Planta;
import servicios.PlantaServicio;

public class MenuPlanta {
	
	public void verPlantas(PlantaServicio plantaServicio) {
        Set<Planta> plantas = plantaServicio.obtenerTodas();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas registradas.");
        } else {
            System.out.println("\n--- Lista de Plantas ---");
            for (Planta planta : plantas) {
                System.out.println("Código: " + planta.getCodigo());
                System.out.println("Nombre Común: " + planta.getNombreComun());
                System.out.println("Nombre Científico: " + planta.getNombreCientifico());
                System.out.println("------------------------");
            }
        }
    }
	
	public void insertarPlanta(PlantaServicio plantaServicio) {
		Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("\n--- Insertar Nueva Planta ---");

	    System.out.print("Ingrese el código de la planta: ");
	    String codigo = scanner.nextLine().trim();

	    System.out.print("Ingrese el nombre común de la planta: ");
	    String nombreComun = scanner.nextLine().trim();

	    System.out.print("Ingrese el nombre científico de la planta: ");
	    String nombreCientifico = scanner.nextLine().trim();

	    // Crear instancia de Planta
	    Planta nuevaPlanta = new Planta(codigo, nombreComun, nombreCientifico);

	    // Llamar al servicio para insertar la planta
	    boolean exito = plantaServicio.agregar(nuevaPlanta);
	    
	    if (exito) {
	        System.out.println("Planta insertada correctamente.");
	    } else {
	        System.out.println("No se pudo insertar la planta. Verifique los datos e intente nuevamente.");
	    }
    }
}
