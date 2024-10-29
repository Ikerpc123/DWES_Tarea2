package daoImpl;

import modelo.Ejemplar;

import java.sql.*;
import java.util.*;

import dao.EjemplarDAO;

public class EjemplarDAOImpl implements EjemplarDAO {
	
	private Connection con;
    PreparedStatement ps;

    public EjemplarDAOImpl (Connection con){
    	this.con = con;
    }
    
    @Override
    public int insertar(Ejemplar ejemplar) {
    	try {
			ps = con.prepareStatement("INSERT INTO ejemplares (id, nombre, idplanta) VALUES (?, ?, ?)");
			ps.setLong(1, ejemplar.getId());
			ps.setString(2, ejemplar.getNombre());
			ps.setString(3, ejemplar.getIdPlanta());
		} catch (SQLException e) {
			System.out.println("Error al insertar ejemplares: " + e.getMessage());
		}
    	
        return 0;
    }
    
    @Override
    public int eliminar(Ejemplar ejemplar) {
    	try {
            ps = con.prepareStatement("DELETE FROM ejemplares WHERE codigo = ?");
            ps.setLong(1, ejemplar.getId());
        } catch (SQLException e) {
            System.out.println("Error al eliminar ejemplares: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modificar(Ejemplar ejemplar) {
    	try {
            ps = con.prepareStatement("UPDATE ejemplares SET nombre = ?, idPlanta = ? WHERE id = ?");
            ps.setString(1, ejemplar.getNombre());
            ps.setString(2, ejemplar.getIdPlanta());
            ps.setLong(3, ejemplar.getId());
        } catch (SQLException e) {
            System.out.println("Error al modificar ejemplares: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Ejemplar findById(long id) {
    	Ejemplar ejemplar = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar(rs.getLong("id"), 
                		rs.getString("nombre"), 
                		rs.getString("idPlanta"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplar;
    }

    @Override
    public Ejemplar findByNombre(String nombre) {
    	Ejemplar ejemplar = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares WHERE nombre = ?");
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar(rs.getLong("id"), rs.getString("nombre"), rs.getString("idPlanta"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplar;
    }

    @Override
    public Set<Ejemplar> findAll() {
    	Set<Ejemplar> ejemplares = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplares");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar(rs.getLong("id"), rs.getString("nombre"), rs.getString("idPlanta"));
                ejemplares.add(ejemplar);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las plantas: " + e.getMessage());
        }
        return ejemplares;
    }
}
