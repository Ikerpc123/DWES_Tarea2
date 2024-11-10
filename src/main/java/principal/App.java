package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import util.MySqlDAOFactory;
import vista.MenuInicial;

/**
 * Clase principal que ejecuta la aplicación.
 * Contiene el método main que inicia la conexión con la base de datos 
 * y muestra el menú inicial de la aplicación.
 */
public class App 
{
    /**
     * Método principal que inicia la aplicación.
     * Establece la conexión con la base de datos y muestra el menú inicial.
     * 
     * @param args Los argumentos de línea de comandos (no utilizados en este caso).
     * @throws IOException Si ocurre un error de entrada/salida durante la ejecución.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public static void main( String[] args ) throws IOException, SQLException
    {
        // Establece la conexión con la base de datos utilizando la fábrica de DAO
        Connection connection = MySqlDAOFactory.getConnection();
        
        // Crea el objeto MenuInicial y le pasa la conexión a la base de datos
        MenuInicial menuInicial = new MenuInicial(connection);
        
        // Muestra el menú inicial en la consola
        menuInicial.mostrarMenuInicial();
    }
}
