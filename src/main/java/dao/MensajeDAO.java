package dao;

import java.util.Date;
import java.util.Set;
import modelo.Mensaje;

/**
 * Interface que define los métodos CRUD y consultas específicas para manejar mensajes en la base de datos.
 */
public interface MensajeDAO {

    /**
     * Inserta un nuevo mensaje en la base de datos.
     *
     * @param mensaje El objeto Mensaje a insertar.
     * @return El número de filas afectadas (1 si se inserta correctamente, 0 en caso contrario).
     */
    int insertar(Mensaje mensaje);

    /**
     * Elimina un mensaje de la base de datos.
     *
     * @param mensaje El objeto Mensaje a eliminar.
     * @return El número de filas afectadas (1 si se elimina correctamente, 0 en caso contrario).
     */
    int eliminar(Mensaje mensaje);

    /**
     * Modifica un mensaje existente en la base de datos.
     *
     * @param mensaje El objeto Mensaje con los datos actualizados.
     * @return El número de filas afectadas (1 si se modifica correctamente, 0 en caso contrario).
     */
    int modificar(Mensaje mensaje);

    /**
     * Busca un mensaje por su ID.
     *
     * @param id El ID del mensaje a buscar.
     * @return El objeto Mensaje correspondiente o null si no se encuentra.
     */
    Mensaje findById(long id);

    /**
     * Busca un mensaje por la fecha de creación.
     *
     * @param fecha La fecha del mensaje a buscar.
     * @return El objeto Mensaje correspondiente o null si no se encuentra.
     */
    Mensaje findByFecha(Date fecha);

    /**
     * Obtiene todos los mensajes de la base de datos.
     *
     * @return Un conjunto de objetos Mensaje.
     */
    Set<Mensaje> findAll();

    /**
     * Obtiene todos los mensajes asociados a un ejemplar específico.
     *
     * @param idEjemplar El ID del ejemplar.
     * @return Un conjunto de objetos Mensaje.
     */
    Set<Mensaje> findMensajesByEjemplarId(long idEjemplar);

    /**
     * Obtiene todos los mensajes realizados por una persona específica.
     *
     * @param idPersona El ID de la persona.
     * @return Un conjunto de objetos Mensaje.
     */
    Set<Mensaje> findByPersona(long idPersona);

    /**
     * Obtiene los mensajes en un rango de fechas específico.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Un conjunto de objetos Mensaje.
     */
    Set<Mensaje> findByFechaRango(String fechaInicio, String fechaFin);

    /**
     * Obtiene los mensajes según el código de una planta.
     *
     * @param codigoPlanta El código de la planta.
     * @return Un conjunto de objetos Mensaje.
     */
    Set<Mensaje> findByPlanta(String codigoPlanta);
}
