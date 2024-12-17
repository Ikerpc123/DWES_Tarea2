package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.EnfermedadDAO;
import modelo.Ejemplar;
import modelo.Enfermedad;

public class EnfermedadDAOImpl implements EnfermedadDAO {

	private Connection con;
    private PreparedStatement ps;
    
	public EnfermedadDAOImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int insertar(Enfermedad enfermedad) {
		try {
            ps = con.prepareStatement(
                "INSERT INTO enfermedades (nombre, sintomas, nociva, cod_planta) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, enfermedad.getNombre());
            ps.setString(2, enfermedad.getSintomas());
            ps.setString(3, enfermedad.isNociva() ? "1" : "0");
            ps.setString(4, enfermedad.getCod_planta());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar la enfermedad: " + e.getMessage());
        }
        return 0;
	}
	
	@Override
	public Enfermedad findById(Long idEnfermedad) {
		Enfermedad enfermedad = null;
        try {
            ps = con.prepareStatement("SELECT * FROM enfermedades WHERE id = ?");
            ps.setLong(1, idEnfermedad);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                enfermedad = new Enfermedad(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("sintomas"),
                        rs.getBoolean("nociva"),
                        rs.getString("cod_planta")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar enfermedades: " + e.getMessage());
        }
        return enfermedad;
	}
	
	@Override
	public Enfermedad findByNombre(String nombre) {
		Enfermedad enfermedad = null;
		try {
			ps = con.prepareStatement("SELECT * FROM enfermedades WHERE nombre = ?");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				enfermedad = new Enfermedad(rs.getLong("id"), rs.getString("nombre"), rs.getString("sintomas"),
						rs.getBoolean("nociva"), rs.getString("cod_planta"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error al buscar enfermedades: " + e.getMessage());
		}
		return enfermedad;
	}
}
