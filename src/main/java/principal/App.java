package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import util.MySqlDAOFactory;
import vista.MenuInicial;

public class App 
{
    public static void main( String[] args ) throws IOException, SQLException
    {
        Connection connection = MySqlDAOFactory.getConnection();
        
        MenuInicial menuInicial = new MenuInicial(connection);
        menuInicial.mostrarMenuInicial();
        
    }
}
