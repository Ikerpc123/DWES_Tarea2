package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ParasitoDAO;
import modelo.Mensaje;
import modelo.Parasito;

public class ParasitoDAOImpl implements ParasitoDAO{

	private Connection con;
	private PreparedStatement ps;
	
	public ParasitoDAOImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int insertar(Parasito parasito) {
		try {
			ps = con.prepareStatement("INSERT INTO parasitos (nombre, color) VALUES (?, ?)");
			ps.setString(1, parasito.getNombre());
			ps.setString(2, parasito.getColor());

			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar el parásito: " + e.getMessage());
		}
		return 0;
	}
	
	@Override
	public List<Parasito> mostrar() {
		
		List<Parasito> parasitos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM parasitos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	parasitos.add(new Parasito(rs.getLong("id"),
                                         rs.getString("nombre"),
                                         rs.getString("color")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los mensajes: " + e.getMessage());
        }
        return parasitos;

	}
	
	@Override
	public void update(Parasito parasito, Long idEnfermedad) {
		try {
			ps = con.prepareStatement("UPDATE parasitos SET id_enfermedad = ? WHERE id = ?");
			ps.setLong(1, idEnfermedad);
			ps.setLong(2, parasito.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar el parásito: " + e.getMessage());
		}
	}
	
	@Override
	public Parasito findById(Long id) {
		try {
            ps = con.prepareStatement("SELECT * FROM parasitos WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Parasito(rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("color"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el parásito: " + e.getMessage());
        }
        return null;
	}
}
