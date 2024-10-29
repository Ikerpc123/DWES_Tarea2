package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dao.PersonaDAO;
import modelo.Persona;
import modelo.Planta;

public class PersonaDAOImpl implements PersonaDAO{
	private Connection con;
    PreparedStatement ps;

    public PersonaDAOImpl (Connection con){
    	this.con = con;
    }

    @Override
    public int insertar(Persona persona) {
    	try {
			ps = con.prepareStatement("INSERT INTO personas (id, nombre, email) VALUES (?, ?, ?)");
			ps.setLong(1, persona.getId());
			ps.setString(2, persona.getNombre());
			ps.setString(3, persona.getEmail());
		} catch (SQLException e) {
			System.out.println("Error al insertar persona: " + e.getMessage());
		}
    	
        return 0;
    }

    @Override
    public int eliminar(Persona persona) {
    	try {
            ps = con.prepareStatement("DELETE FROM personas WHERE id = ?");
            ps.setLong(1, persona.getId());
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modificar(Persona persona) {
    	try {
            ps = con.prepareStatement("UPDATE personas SET nombre = ?, email = ? WHERE id = ?");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());
            ps.setLong(3, persona.getId());
        } catch (SQLException e) {
            System.out.println("Error al modificar persona: " + e.getMessage());
        }
        return 0;
    }

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
            System.out.println("Error al buscar persona: " + e.getMessage());
        }
        return persona;
    }

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
            System.out.println("Error al buscar persona: " + e.getMessage());
        }
        return persona;
    }

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
