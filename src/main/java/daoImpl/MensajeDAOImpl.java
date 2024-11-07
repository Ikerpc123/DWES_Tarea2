package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import modelo.Mensaje;
import dao.MensajeDAO;

public class MensajeDAOImpl implements MensajeDAO{
	private Connection con;
    PreparedStatement ps;

    public MensajeDAOImpl (Connection con){
    	this.con = con;
    }
    
    @Override
    public int insertar(Mensaje mensaje) {
    	try {
			ps = con.prepareStatement("INSERT INTO mensajes (fechahora, mensaje, persona_id, ejemplar_id) VALUES (?, ?, ?, ?)");
			ps.setTimestamp(1, new Timestamp(mensaje.getFechaHora().getTime()));
			ps.setString(2, mensaje.getMensaje());
			ps.setLong(3, mensaje.getPersona());
			ps.setLong(4, mensaje.getEjemplar());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar el mensaje: " + e.getMessage());
		}
    	
        return 0;
    }
    
    @Override
    public int eliminar(Mensaje mensaje) {
    	try {
            ps = con.prepareStatement("DELETE FROM mensajes WHERE codigo = ?");
            ps.setLong(1, mensaje.getId());
        } catch (SQLException e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int modificar(Mensaje mensaje) {
    	try {
            ps = con.prepareStatement("UPDATE mensajes SET fechahora = ?, mensaje = ?, persona_id = ?, ejemplar_id = ?  WHERE id = ?");
            ps.setTimestamp(1, new Timestamp(mensaje.getFechaHora().getTime()));
            ps.setString(2, mensaje.getMensaje());
            ps.setLong(3, mensaje.getPersona());
            ps.setLong(3, mensaje.getEjemplar());
        } catch (SQLException e) {
            System.out.println("Error al modificar el mensaje: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Mensaje findById(long id) {
    	Mensaje mensaje = null;
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mensaje = new Mensaje(rs.getLong("id"), 
                		rs.getDate("fechahora"), 
                		rs.getString("mensaje"),
                		rs.getLong("persona_id"),
                		rs.getLong("ejemplar_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return mensaje;
    }

    @Override
    public Mensaje findByFecha(Date fecha) {
    	Mensaje mensaje = null;
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes WHERE fechahora = ?");
            ps.setTimestamp(1, new Timestamp(fecha.getTime()));
            //ps.setDate(1, fecha);;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mensaje = new Mensaje(rs.getLong("id"), 
                		rs.getDate("fechahora"), 
                		rs.getString("mensaje"),
                		rs.getLong("persona_id"),
                		rs.getLong("ejemplar_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar el mensaje: " + e.getMessage());
        }
        return mensaje;
    }

    @Override
    public Set<Mensaje> findAll() {
    	Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mensaje ejemplar = new Mensaje(rs.getLong("id"), 
                		rs.getDate("fechahora"), 
                		rs.getString("mensaje"),
                		rs.getLong("persona_id"),
                		rs.getLong("ejemplar_id"));
                mensajes.add(ejemplar);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las plantas: " + e.getMessage());
        }
        return mensajes;
    }
}
