package org.tarea2;

import com.mysql.cj.jdbc.MysqlDataSource;
import modelo.Planta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "INI!" );

        Scanner in = new Scanner(System.in);

        System.out.println( "Dame el código de una nueva Planta" );
        String codigo = in.nextLine().trim().toUpperCase();
        System.out.println( "Dame el nombre común de la nueva Planta" );
        String nombreComun= in.nextLine().trim();
        System.out.println( "Dame el nombre  de la nueva Planta" );
        String nombreCientifico= in.nextLine();

        Planta nuevaPlanta = new Planta(codigo, nombreComun, nombreCientifico);

        Connection con;
        MysqlDataSource ds = new MysqlDataSource();
        Properties prop = null;
        FileInputStream fis;


        String url = "jdbc:mysql://localhost:3306/tarea2dwes";
        String usuario = "root";
        String password = "";

        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);
            url = prop.getProperty("url");
            usuario = prop.getProperty("usuario");
            password = prop.getProperty("password");

            ds.setUrl(url);
            ds.setUser(usuario);
            ds.setPassword(password);

            con = ds.getConnection();

            String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES("+ nuevaPlanta.getCodigo()+" "+ nuevaPlanta.getNombreComun() + " " + nuevaPlanta.getNombreCientifico() + ")";


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Ha habido un error en la conexion con la base de datos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println( "FIN" );
    }
}
