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

	    Planta nuevaPlanta = new Planta(codigo, nombreComun, nombreCientifico);

	    boolean exito = plantaServicio.agregar(nuevaPlanta);
	    
	    if (exito) {
	        System.out.println("Planta insertada correctamente.");
	    } else {
	        System.out.println("No se pudo insertar la planta. Verifique los datos e intente nuevamente.");
	    }
    }
	

	public void gestionarPlanta(PlantaServicio plantaServicio)
	{
		Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Plantas ---");
            System.out.println("1. Insertar una nueva planta");
            System.out.println("2. Modificar una planta");
            System.out.println("3. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                	insertarPlanta(plantaServicio);
                    break;
                case 2:
                	modificarPlanta(plantaServicio);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);

	}
	
	public void modificarPlanta(PlantaServicio plantaServicio)
	{
		Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el código de la planta a modificar: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre común de la planta: ");
        String nuevoNombreComun = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre científico de la planta: ");
        String nuevoNombreCientifico = scanner.nextLine();

        Planta planta = new Planta(codigo, nuevoNombreComun, nuevoNombreCientifico);

        boolean exito = plantaServicio.actualizar(planta);
        if (exito) {
            System.out.println("Planta actualizada exitosamente.");
        } else {
            System.out.println("No se pudo actualizar la planta. Verifique los datos ingresados.");
        }
	}
}
