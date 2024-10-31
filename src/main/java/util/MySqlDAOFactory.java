package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDAOFactory {
	
	Connection con;
	
	private MySqlDAOFactory() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarea2dwes", "root", "");
			
		}
		catch(SQLException e) {
			System.out.println("No ha sido posible establecer la conexión: " + e.getMessage());
		}
	}

    private static MysqlDataSource ds = new MysqlDataSource();

    public static Connection getConnection() throws IOException, SQLException {
        Properties prop = new Properties();
        FileInputStream fis = null;
        Connection con = null;

        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);
            String url = prop.getProperty("url");
            String usuario = prop.getProperty("usuario");
            String password = prop.getProperty("password");

            ds.setUrl(url);
            ds.setUser(usuario);
            ds.setPassword(password);

            con = ds.getConnection();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo de propiedades no encontrado", e);
        } catch (SQLException e) {
            System.out.println("Ha habido un error en la conexión con la base de datos");
            throw e;  
        } finally {
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
