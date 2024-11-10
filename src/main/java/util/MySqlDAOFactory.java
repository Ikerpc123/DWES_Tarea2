package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Clase de fábrica para obtener conexiones a una base de datos MySQL.
 * 
 * Esta clase está diseñada para proporcionar un mecanismo centralizado para obtener conexiones a la
 * base de datos, utilizando propiedades configurables desde un archivo externo.
 * Utiliza el controlador MySQL JDBC {@code MysqlDataSource} para establecer la conexión y permite
 * cargar las configuraciones de la conexión desde un archivo de propiedades.
 * 
 * La clase gestiona la conexión a la base de datos de forma eficiente y maneja los errores asociados
 * con la carga de propiedades y la conexión.
 */
public class MySqlDAOFactory {

    private Connection con;

    /**
     * Constructor privado que intenta establecer una conexión con la base de datos usando
     * la URL predeterminada, el usuario y la contraseña.
     * 
     * Este constructor se utiliza para establecer una conexión predeterminada al momento de
     * crear una instancia de la clase, aunque en esta clase no se usa instanciación directa,
     * sino que se usa el método estático {@link #getConnection()}.
     */
    private MySqlDAOFactory() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarea2dwes", "root", "");
        } catch (SQLException e) {
            System.out.println("No ha sido posible establecer la conexión: " + e.getMessage());
        }
    }

    // Fuente de datos MySQL para la conexión.
    private static MysqlDataSource ds = new MysqlDataSource();

    /**
     * Obtiene una conexión a la base de datos utilizando los parámetros definidos en el archivo
     * de propiedades {@code db.properties}.
     * 
     * El archivo de propiedades debe estar ubicado en la ruta {@code src/main/resources/db.properties}
     * y debe contener las claves: {@code url}, {@code usuario}, {@code password}.
     * 
     * Si se encuentra un error al leer el archivo de propiedades o al establecer la conexión,
     * el método lanza excepciones que deben ser manejadas.
     * 
     * @return La conexión establecida a la base de datos.
     * @throws IOException Si ocurre un error al leer el archivo de propiedades.
     * @throws SQLException Si ocurre un error al intentar establecer la conexión con la base de datos.
     */
    public static Connection getConnection() throws IOException, SQLException {
        Properties prop = new Properties();
        FileInputStream fis = null;
        Connection con = null;

        try {
            // Cargar las propiedades del archivo
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);
            
            // Obtener los valores de las propiedades
            String url = prop.getProperty("url");
            String usuario = prop.getProperty("usuario");
            String password = prop.getProperty("password");

            // Establecer los parámetros de la conexión
            ds.setUrl(url);
            ds.setUser(usuario);
            ds.setPassword(password);

            // Obtener la conexión desde el datasource
            con = ds.getConnection();
        } catch (FileNotFoundException e) {
            // Excepciones si el archivo de propiedades no es encontrado
            throw new RuntimeException("Archivo de propiedades no encontrado", e);
        } catch (SQLException e) {
            // Manejo de errores de conexión
            System.out.println("Ha habido un error en la conexión con la base de datos");
            throw e;
        } finally {
            // Cerrar el archivo de propiedades si se abrió correctamente
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de propiedades");
                }
            }
        }

        return con;
    }
}
