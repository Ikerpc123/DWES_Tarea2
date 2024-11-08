package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    
    @Override
    public Set<Mensaje> findMensajesByEjemplarId(long idEjemplar) {
    	Set<Mensaje> mensajes = new HashSet<>();
        try {
            // Realizar un JOIN para obtener el nombre de la persona que anotó el mensaje
            ps = con.prepareStatement(
                "SELECT m.*, p.nombre AS persona_nombre " +
                "FROM mensajes m " +
                "JOIN personas p ON m.persona_id = p.id " +
                "WHERE m.ejemplar_id = ? " +
                "ORDER BY m.fechahora ASC"
            );
            ps.setLong(1, idEjemplar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Crear el objeto Mensaje con los datos existentes
                Mensaje mensaje = new Mensaje(
                    rs.getLong("id"),
                    rs.getTimestamp("fechahora"),
                    rs.getString("mensaje"),
                    rs.getLong("persona_id"),
                    rs.getLong("ejemplar_id")
                );

                // Obtener el nombre de la persona directamente del ResultSet
                String personaNombre = rs.getString("persona_nombre");

                // Mostrar el mensaje y el nombre de la persona
                System.out.println("Fecha/Hora: " + mensaje.getFechaHora());
                System.out.println("Mensaje: " + mensaje.getMensaje());
                System.out.println("Anotado por: " + personaNombre);
                System.out.println("-----------------------------");

                // Agregar el mensaje a la lista si es necesario
                mensajes.add(mensaje);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes del ejemplar: " + e.getMessage());
        }
        return mensajes;
    }
    
    @Override
    public Set<Mensaje> findByPersona(long idPersona) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            // Consulta para obtener los mensajes de una persona específica
            ps = con.prepareStatement(
                "SELECT m.*, p.nombre AS persona_nombre " +
                "FROM mensajes m " +
                "JOIN personas p ON m.persona_id = p.id " +
                "WHERE m.persona_id = ? " +
                "ORDER BY m.fechahora ASC"
            );
            ps.setLong(1, idPersona);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                    rs.getLong("id"),
                    rs.getTimestamp("fechahora"),
                    rs.getString("mensaje"),
                    rs.getLong("persona_id"),
                    rs.getLong("ejemplar_id")
                );
                mensajes.add(mensaje);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por persona: " + e.getMessage());
        }
        return mensajes;
    }

    @Override
    public Set<Mensaje> findByFechaRango(String fechaInicio, String fechaFin) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            // Consulta para obtener los mensajes dentro de un rango de fechas
            ps = con.prepareStatement(
                "SELECT m.*, p.nombre AS persona_nombre " +
                "FROM mensajes m " +
                "JOIN personas p ON m.persona_id = p.id " +
                "WHERE m.fechahora BETWEEN ? AND ? " +
                "ORDER BY m.fechahora ASC"
            );
            ps.setTimestamp(1, Timestamp.valueOf(fechaInicio + " 00:00:00"));
            ps.setTimestamp(2, Timestamp.valueOf(fechaFin + " 23:59:59"));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                    rs.getLong("id"),
                    rs.getTimestamp("fechahora"),
                    rs.getString("mensaje"),
                    rs.getLong("persona_id"),
                    rs.getLong("ejemplar_id")
                );
                mensajes.add(mensaje);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por rango de fechas: " + e.getMessage());
        }
        return mensajes;
    }

    @Override
    public Set<Mensaje> findByPlanta(String codigoPlanta) {
    	Set<Mensaje> mensajes = new HashSet<>();
        try {
            // Consulta para obtener los mensajes según el tipo de planta
            ps = con.prepareStatement(
                "SELECT m.*, p.nombre AS persona_nombre " +
                "FROM mensajes m " +
                "JOIN ejemplares e ON m.ejemplar_id = e.id " +
                "JOIN plantas pl ON e.planta_id = pl.id " +
                "JOIN personas p ON m.persona_id = p.id " +
                "WHERE pl.codigo = ? " +
                "ORDER BY m.fechahora ASC"
            );
            ps.setString(1, codigoPlanta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                    rs.getLong("id"),
                    rs.getTimestamp("fechahora"),
                    rs.getString("mensaje"),
                    rs.getLong("persona_id"),
                    rs.getLong("ejemplar_id")
                );
                mensajes.add(mensaje);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por tipo de planta: " + e.getMessage());
        }
        return mensajes;
    }
}
