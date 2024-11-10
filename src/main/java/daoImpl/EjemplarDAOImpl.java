package daoImpl;

import modelo.Ejemplar;
import dao.EjemplarDAO;

import java.sql.*;
import java.util.*;

/**
 * Implementación de la interfaz EjemplarDAO para la gestión de ejemplares en la base de datos.
 */
public class EjemplarDAOImpl implements EjemplarDAO {

    private Connection con;
    private PreparedStatement ps;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param con La conexión a la base de datos.
     */
    public EjemplarDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertar(Ejemplar ejemplar) {
        try {
            ps = con.prepareStatement("INSERT INTO ejemplares (idplanta) VALUES (?)");
            ps.setString(1, ejemplar.getIdPlanta());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar ejemplares: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int eliminar(Ejemplar ejemplar) {
        try {
            ps = con.prepareStatement("DELETE FROM ejemplares WHERE codigo = ?");
            ps.setLong(1, ejemplar.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar ejemplares: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int modificar(Ejemplar ejemplar) {
        try {
            ps = con.prepareStatement("UPDATE ejemplares SET nombre = ? WHERE id = ?");
            ps.setString(1, ejemplar.getIdPlanta() + "_" + ejemplar.getId());
            ps.setLong(2, ejemplar.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar ejemplares: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ejemplar findById(long id) {
        Ejemplar ejemplar = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("idPlanta")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ejemplar findByNombre(String nombre) {
        Ejemplar ejemplar = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares WHERE nombre = ?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("idPlanta")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ejemplar> findAll() {
        Set<Ejemplar> ejemplares = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("idPlanta")
                );
                ejemplares.add(ejemplar);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las plantas: " + e.getMessage());
        }
        return ejemplares;
    }
}
