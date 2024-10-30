package daoImpl;

import modelo.Planta;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

import dao.PlantaDAO;

public class PlantaDAOImpl implements PlantaDAO {

    private Connection con;
    PreparedStatement ps;

    public PlantaDAOImpl (Connection con){
    	this.con = con;
    }

    @Override
    public int insertar(Planta planta) {
    	try {
			ps = con.prepareStatement("INSERT INTO plantas (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)");
			ps.setString(1, planta.getCodigo());
			ps.setString(2, planta.getNombreComun());
			ps.setString(3, planta.getNombreCientifico());
		} catch (SQLException e) {
			System.out.println("Error al insertar la planta: " + e.getMessage());
		}
    	
        return 0;
    }

    @Override
    public int eliminar(Planta planta) {
    	try {
            ps = con.prepareStatement("DELETE FROM plantas WHERE codigo = ?");
            ps.setString(1, planta.getCodigo());
        } catch (SQLException e) {
            System.out.println("Error al eliminar la planta: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modificar(Planta planta) {
    	try {
            ps = con.prepareStatement("UPDATE plantas SET nombrecomun = ?, nombrecientifico = ? WHERE codigo = ?");
            ps.setString(1, planta.getNombreComun());
            ps.setString(2, planta.getNombreCientifico());
            ps.setString(3, planta.getCodigo());
        } catch (SQLException e) {
            System.out.println("Error al modificar la planta: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Planta findById(String idplanta) {
    	Planta planta = null;
        try {
            ps = con.prepareStatement("SELECT * FROM plantas WHERE codigo = ?");
            ps.setString(1, idplanta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                planta = new Planta(rs.getString("codigo"), rs.getString("nombrecomun"), rs.getString("nombrecientifico"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar la planta: " + e.getMessage());
        }
        return planta;
    }

    @Override
    public Planta findByNombre(String nombreComun) {
    	Planta planta = null;
        try {
            ps = con.prepareStatement("SELECT * FROM plantas WHERE nombrecomun = ?");
            ps.setString(1, nombreComun);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                planta = new Planta(rs.getString("codigo"), rs.getString("nombrecomun"), rs.getString("nombrecientifico"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar la planta: " + e.getMessage());
        }
        
        return planta;
    }

    @Override
    public Set<Planta> findAll() {
    	Set<Planta> plantas = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM plantas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Planta planta = new Planta(rs.getString("codigo"), rs.getString("nombrecomun"), rs.getString("nombrecientifico"));
                plantas.add(planta);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las plantas: " + e.getMessage());
        }
        return plantas;
    }
}
