package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import daoImpl.PlantaDAOImpl;
import servicios.PlantaServicio;
import servicioImpl.PlantaServicioImpl;
import util.MySqlDAOFactory;
import vista.MenuPlanta;

public class App 
{
    public static void main( String[] args ) throws IOException, SQLException
    {
    	// Intento de conexión a la base de datos
        Connection connection = MySqlDAOFactory.getConnection();
        
     // Instancia de PlantaDAO y PlantaServicio
        PlantaDAOImpl plantaDAO = new PlantaDAOImpl(connection);
        PlantaServicio plantaServicio = new PlantaServicioImpl(plantaDAO);
        
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ver Plantas");
            System.out.println("2. Insertar Plantas");
            System.out.println("3. Opción 3");
            System.out.println("4. Opción 4");
            System.out.println("5. Opción 5");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	MenuPlanta verPlanta = new MenuPlanta();
                	verPlanta.verPlantas(plantaServicio);
                    break;
                case 2:
                	MenuPlanta metePlanta = new MenuPlanta();
                	metePlanta.insertarPlanta(plantaServicio);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
