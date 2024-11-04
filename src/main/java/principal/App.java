package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import daoImpl.PlantaDAOImpl;
import servicios.PlantaServicio;
import servicioImpl.PlantaServicioImpl;
import util.MySqlDAOFactory;
import vista.MenuInicial;
import vista.MenuPlanta;

public class App 
{
    public static void main( String[] args ) throws IOException, SQLException
    {
        Connection connection = MySqlDAOFactory.getConnection();
        
        MenuInicial menuInicial = new MenuInicial(connection);
        menuInicial.mostrarMenuInicial();
        
    }
}
