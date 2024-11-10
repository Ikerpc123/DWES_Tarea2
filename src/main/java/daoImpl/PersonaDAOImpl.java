package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import dao.PersonaDAO;
import modelo.Persona;

/**
 * Implementación de la interfaz PersonaDAO para la gestión de personas en la base de datos.
 */
public class PersonaDAOImpl implements PersonaDAO {
    private Connection con;
    private PreparedStatement ps;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     * 
     * @param con La conexión a la base de datos.
     */
    public PersonaDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertar(Persona persona) {
        try {
            ps = con.prepareStatement("INSERT INTO personas (nombre, email) VALUES (?, ?)");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int eliminar(Persona persona) {
        try {
            ps = con.prepareStatement("DELETE FROM personas WHERE id = ?");
            ps.setLong(1, persona.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int modificar(Persona persona) {
        try {
            ps = con.prepareStatement("UPDATE personas SET nombre = ?, email = ? WHERE id = ?");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());
            ps.setLong(3, persona.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar persona: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Persona findById(Long id) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM personas WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar persona por ID: " + e.getMessage());
        }
        return persona;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Persona findByNombre(String nombre) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM personas WHERE nombre = ?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar persona por nombre: " + e.getMessage());
        }
        return persona;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Persona findByEmail(String email) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM personas WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar persona por email: " + e.getMessage());
        }
        return persona;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Persona> findAll() {
        Set<Persona> personas = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM personas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
                personas.add(persona);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las personas: " + e.getMessage());
        }
        return personas;
    }
}
