package servicioImpl;

import java.util.Set;

import dao.MensajeDAO;
import modelo.Mensaje;
import servicios.MensajeServicio;

/**
 * Implementación del servicio de mensajes. Proporciona operaciones para validar, insertar,
 * y filtrar mensajes según varios criterios como el ejemplar, la persona, el rango de fechas
 * y el tipo de planta. También ofrece un método para obtener todos los mensajes.
 */
public class MensajeServicioImpl implements MensajeServicio {

    private MensajeDAO mensajeDAO;

    /**
     * Constructor que inicializa el objeto DAO de mensajes.
     *
     * @param mensajeDAO El DAO de mensajes.
     */
    public MensajeServicioImpl(MensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    /**
     * Valida si un mensaje es válido. Un mensaje es válido si tiene contenido
     * y está asociado a una persona y un ejemplar.
     *
     * @param mensaje El mensaje a validar.
     * @return {@code true} si el mensaje es válido, {@code false} en caso contrario.
     */
    @Override
    public boolean validarMensaje(Mensaje mensaje) {
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isEmpty()) {
            System.out.println("Error: El mensaje no puede estar vacío.");
            return false;
        }
        if (mensaje.getPersona() == null || mensaje.getEjemplar() == null) {
            System.out.println("Error: El mensaje debe tener una persona y un ejemplar asignados.");
            return false;
        }
        return true;
    }

    /**
     * Inserta un mensaje en la base de datos si es válido.
     *
     * @param mensaje El mensaje a insertar.
     * @return {@code true} si el mensaje fue insertado correctamente, {@code false} si el mensaje no es válido.
     */
    @Override
    public boolean insertarMensaje(Mensaje mensaje) {
        if (validarMensaje(mensaje)) {
            int resultado = mensajeDAO.insertar(mensaje);
            return resultado > 0;
        }
        return false;
    }

    /**
     * Obtiene todos los mensajes almacenados en la base de datos.
     *
     * @return Un conjunto con todos los mensajes.
     */
    @Override
    public Set<Mensaje> obtenerTodosMensajes() {
        return mensajeDAO.findAll();
    }

    /**
     * Obtiene todos los mensajes relacionados con un ejemplar específico.
     *
     * @param idEjemplar El ID del ejemplar.
     * @return Un conjunto con los mensajes asociados a ese ejemplar.
     */
    @Override
    public Set<Mensaje> obtenerMensajesPorEjemplar(long idEjemplar) {
        return mensajeDAO.findMensajesByEjemplarId(idEjemplar);
    }

    /**
     * Anota un nuevo mensaje asociando un ejemplar, una persona y el contenido del mensaje.
     *
     * @param idEjemplar El ID del ejemplar al que se asocia el mensaje.
     * @param idPersona El ID de la persona que crea el mensaje.
     * @param contenido El contenido del mensaje.
     * @return {@code true} si el mensaje fue anotado correctamente, {@code false} si hubo un error.
     */
    public boolean anotarMensaje(long idEjemplar, long idPersona, String contenido) {
        Mensaje mensaje = new Mensaje();
        mensaje.setEjemplar(idEjemplar);
        mensaje.setPersona(idPersona);
        mensaje.setMensaje(contenido);
        return mensajeDAO.insertar(mensaje) > 0;
    }

    /**
     * Filtra los mensajes según la persona que los creó, mostrando solo los mensajes
     * asociados al ID de persona especificado.
     *
     * @param idPersona El ID de la persona cuyos mensajes se desean filtrar.
     * @return Un conjunto de mensajes creados por la persona especificada.
     */
    @Override
    public Set<Mensaje> filtrarMensajesPorPersona(long idPersona) {
        return mensajeDAO.findByPersona(idPersona);
    }

    /**
     * Filtra los mensajes por un rango de fechas, mostrando solo los mensajes creados
     * dentro de ese rango.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Un conjunto de mensajes dentro del rango de fechas especificado.
     */
    @Override
    public Set<Mensaje> filtrarMensajesPorRangoFechas(String fechaInicio, String fechaFin) {
        return mensajeDAO.findByFechaRango(fechaInicio, fechaFin);
    }

    /**
     * Filtra los mensajes según el tipo de planta, mostrando solo los mensajes
     * relacionados con una planta específica.
     *
     * @param codigoPlanta El código de la planta.
     * @return Un conjunto de mensajes relacionados con la planta especificada.
     */
    @Override
    public Set<Mensaje> filtrarMensajesPorTipoPlanta(String codigoPlanta) {
        return mensajeDAO.findByPlanta(codigoPlanta);
    }
}
