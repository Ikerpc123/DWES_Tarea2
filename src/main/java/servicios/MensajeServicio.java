package servicios;

import java.util.Set;
import modelo.Mensaje;

/**
 * Interfaz que define los servicios relacionados con los mensajes.
 * Proporciona métodos para validar, insertar y filtrar mensajes, así como obtener mensajes
 * por distintos criterios, como el ejemplar, la persona, el rango de fechas y el tipo de planta.
 */
public interface MensajeServicio {

    /**
     * Valida si un mensaje es válido según las reglas de negocio.
     *
     * @param mensaje El mensaje a validar.
     * @return {@code true} si el mensaje es válido, {@code false} en caso contrario.
     */
    boolean validarMensaje(Mensaje mensaje);

    /**
     * Inserta un mensaje en la base de datos si es válido.
     *
     * @param mensaje El mensaje a insertar.
     * @return {@code true} si el mensaje fue insertado correctamente, {@code false} en caso contrario.
     */
    boolean insertarMensaje(Mensaje mensaje);

    /**
     * Obtiene todos los mensajes almacenados en el sistema.
     *
     * @return Un conjunto de todos los mensajes.
     */
    Set<Mensaje> obtenerTodosMensajes();

    /**
     * Obtiene todos los mensajes asociados a un ejemplar específico.
     *
     * @param idEjemplar El ID del ejemplar.
     * @return Un conjunto de mensajes asociados al ejemplar.
     */
    Set<Mensaje> obtenerMensajesPorEjemplar(long idEjemplar);

    /**
     * Filtra los mensajes por persona, mostrando solo los mensajes asociados a un ID de persona específico.
     *
     * @param idPersona El ID de la persona.
     * @return Un conjunto de mensajes filtrados por la persona.
     */
    Set<Mensaje> filtrarMensajesPorPersona(long idPersona);

    /**
     * Filtra los mensajes según un rango de fechas, mostrando solo los mensajes dentro de ese rango.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Un conjunto de mensajes dentro del rango de fechas especificado.
     */
    Set<Mensaje> filtrarMensajesPorRangoFechas(String fechaInicio, String fechaFin);

    /**
     * Filtra los mensajes por tipo de planta, mostrando solo los mensajes relacionados con una planta específica.
     *
     * @param codigoPlanta El código de la planta.
     * @return Un conjunto de mensajes filtrados por el tipo de planta.
     */
    Set<Mensaje> filtrarMensajesPorTipoPlanta(String codigoPlanta);
}
