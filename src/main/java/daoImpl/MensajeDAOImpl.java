package daoImpl;

import java.sql.*;
import java.util.*;
import java.util.Date;

import modelo.Mensaje;
import dao.MensajeDAO;

/**
 * Implementación de la interfaz MensajeDAO para la gestión de mensajes en la base de datos.
 */
public class MensajeDAOImpl implements MensajeDAO {

    private Connection con;
    private PreparedStatement ps;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param con La conexión a la base de datos.
     */
    public MensajeDAOImpl(Connection con) {
        this.con = con;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertar(Mensaje mensaje) {
        try {
            ps = con.prepareStatement("INSERT INTO mensajes (fechahora, mensaje, persona_id, ejemplar_id) VALUES (?, ?, ?, ?)");
            ps.setTimestamp(1, new Timestamp(mensaje.getFechaHora().getTime()));
            ps.setString(2, mensaje.getMensaje());
            ps.setLong(3, mensaje.getPersona());
            ps.setLong(4, mensaje.getEjemplar());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar el mensaje: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int eliminar(Mensaje mensaje) {
        try {
            ps = con.prepareStatement("DELETE FROM mensajes WHERE id = ?");
            ps.setLong(1, mensaje.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int modificar(Mensaje mensaje) {
        try {
            ps = con.prepareStatement("UPDATE mensajes SET fechahora = ?, mensaje = ?, persona_id = ?, ejemplar_id = ? WHERE id = ?");
            ps.setTimestamp(1, new Timestamp(mensaje.getFechaHora().getTime()));
            ps.setString(2, mensaje.getMensaje());
            ps.setLong(3, mensaje.getPersona());
            ps.setLong(4, mensaje.getEjemplar());
            ps.setLong(5, mensaje.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar el mensaje: " + e.getMessage());
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mensaje findById(long id) {
        Mensaje mensaje = null;
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mensaje = new Mensaje(rs.getLong("id"),
                                      rs.getTimestamp("fechahora"),
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Mensaje findByFecha(Date fecha) {
        Mensaje mensaje = null;
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes WHERE fechahora = ?");
            ps.setTimestamp(1, new Timestamp(fecha.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mensaje = new Mensaje(rs.getLong("id"),
                                      rs.getTimestamp("fechahora"),
                                      rs.getString("mensaje"),
                                      rs.getLong("persona_id"),
                                      rs.getLong("ejemplar_id"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar el mensaje por fecha: " + e.getMessage());
        }
        return mensaje;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mensaje> findAll() {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mensajes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensajes.add(new Mensaje(rs.getLong("id"),
                                         rs.getTimestamp("fechahora"),
                                         rs.getString("mensaje"),
                                         rs.getLong("persona_id"),
                                         rs.getLong("ejemplar_id")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los mensajes: " + e.getMessage());
        }
        return mensajes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mensaje> findMensajesByEjemplarId(long idEjemplar) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement(
                "SELECT * FROM mensajes WHERE ejemplar_id = ? ORDER BY fechahora ASC"
            );
            ps.setLong(1, idEjemplar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensajes.add(new Mensaje(rs.getLong("id"),
                                         rs.getTimestamp("fechahora"),
                                         rs.getString("mensaje"),
                                         rs.getLong("persona_id"),
                                         rs.getLong("ejemplar_id")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes por ID de ejemplar: " + e.getMessage());
        }
        return mensajes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mensaje> findByPersona(long idPersona) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement(
                "SELECT * FROM mensajes WHERE persona_id = ? ORDER BY fechahora ASC"
            );
            ps.setLong(1, idPersona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensajes.add(new Mensaje(rs.getLong("id"),
                                         rs.getTimestamp("fechahora"),
                                         rs.getString("mensaje"),
                                         rs.getLong("persona_id"),
                                         rs.getLong("ejemplar_id")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por persona: " + e.getMessage());
        }
        return mensajes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mensaje> findByFechaRango(String fechaInicio, String fechaFin) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement(
                "SELECT * FROM mensajes WHERE fechahora BETWEEN ? AND ? ORDER BY fechahora ASC"
            );
            ps.setTimestamp(1, Timestamp.valueOf(fechaInicio + " 00:00:00"));
            ps.setTimestamp(2, Timestamp.valueOf(fechaFin + " 23:59:59"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensajes.add(new Mensaje(rs.getLong("id"),
                                         rs.getTimestamp("fechahora"),
                                         rs.getString("mensaje"),
                                         rs.getLong("persona_id"),
                                         rs.getLong("ejemplar_id")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por rango de fechas: " + e.getMessage());
        }
        return mensajes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Mensaje> findByPlanta(String codigoPlanta) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement(
                "SELECT m.* FROM mensajes m " +
                "JOIN ejemplares e ON m.ejemplar_id = e.id " +
                "JOIN plantas p ON e.planta_id = p.id " +
                "WHERE p.codigo = ? ORDER BY m.fechahora ASC"
            );
            ps.setString(1, codigoPlanta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mensajes.add(new Mensaje(rs.getLong("id"),
                                         rs.getTimestamp("fechahora"),
                                         rs.getString("mensaje"),
                                         rs.getLong("persona_id"),
                                         rs.getLong("ejemplar_id")));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al filtrar mensajes por código de planta: " + e.getMessage());
        }
        return mensajes;
    }
}
