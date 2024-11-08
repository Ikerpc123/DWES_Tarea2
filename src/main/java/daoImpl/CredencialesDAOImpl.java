package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dao.CredencialesDAO;
import modelo.Credenciales;

public class CredencialesDAOImpl implements CredencialesDAO{
	private Connection con;
    PreparedStatement ps;

    public CredencialesDAOImpl (Connection con){
    	this.con = con;
    }

    @Override
    public int insertar(Credenciales credencial) {
    	try {
			ps = con.prepareStatement("INSERT INTO credenciales (usuario, password, persona_id) VALUES (?, ?, ?)");
			ps.setString(1, credencial.getUsuario());
			ps.setString(2, credencial.getPassword());
			ps.setLong(3, credencial.getPersona());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar las credenciales: " + e.getMessage());
		}
    	
        return 0;
    }

    @Override
    public int eliminar(Credenciales credencial) {
    	try {
            ps = con.prepareStatement("DELETE FROM credenciales WHERE id = ?");
            ps.setLong(1, credencial.getId());
        } catch (SQLException e) {
            System.out.println("Error al eliminar las credenciales: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modificar(Credenciales credencial) {
    	try {
            ps = con.prepareStatement("UPDATE credenciales SET usuario = ?, password = ?, persona_id = ? WHERE id = ?");
            ps.setString(1, credencial.getUsuario());
            ps.setString(2, credencial.getPassword());
            ps.setLong(3, credencial.getPersona());
            ps.setLong(4, credencial.getId());
        } catch (SQLException e) {
            System.out.println("Error al modificar las credenciales: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Credenciales findById(Long id) {
    	Credenciales credencial = null;
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	credencial = new Credenciales(rs.getLong("id"),
            			rs.getString("usuario"),
            			rs.getString("password"),
            			rs.getLong("persona_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar las credenciales: " + e.getMessage());
        }
        return credencial;
    }

    @Override
    public Credenciales findByUsuario(String usuario) {
    	Credenciales credencial = null;
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales WHERE usuario = ?");
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	credencial = new Credenciales(rs.getLong("id"),
            			rs.getString("usuario"),
            			rs.getString("password"),
            			rs.getLong("persona_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar las credenciales: " + e.getMessage());
        }
        return credencial;
    }

    @Override
    public Set<Credenciales> findAll() {
    	Set<Credenciales> credenciales = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Credenciales credencial = new Credenciales(rs.getLong("id"),
            			rs.getString("usuario"),
            			rs.getString("password"),
            			rs.getLong("persona_id"));
            	credenciales.add(credencial);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las plantas: " + e.getMessage());
        }
        return credenciales;
    }
}
