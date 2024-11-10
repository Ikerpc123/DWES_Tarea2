package servicios;

import java.util.Set;
import modelo.Ejemplar;

/**
 * Interfaz que define los métodos relacionados con los servicios de ejemplares.
 * Proporciona operaciones para agregar, eliminar, modificar ejemplares,
 * obtener un ejemplar por su ID y obtener todos los ejemplares.
 */
public interface EjemplarServicio {
    
    /**
     * Agrega un nuevo ejemplar.
     *
     * @param ejemplar El ejemplar a agregar.
     * @return {@code true} si el ejemplar fue agregado correctamente, {@code false} en caso contrario.
     */
    boolean agregarEjemplar(Ejemplar ejemplar);
    
    /**
     * Elimina un ejemplar identificado por su ID.
     *
     * @param id El ID del ejemplar a eliminar.
     * @return {@code true} si el ejemplar fue eliminado correctamente, {@code false} si no se encontró el ejemplar.
     */
    boolean eliminarEjemplar(long id);
    
    /**
     * Modifica un ejemplar existente.
     *
     * @param ejemplar El ejemplar con los datos a modificar.
     * @return {@code true} si el ejemplar fue modificado correctamente, {@code false} en caso contrario.
     */
    boolean modificarEjemplar(Ejemplar ejemplar);
    
    /**
     * Obtiene un ejemplar por su ID.
     *
     * @param id El ID del ejemplar a obtener.
     * @return El ejemplar correspondiente al ID, o {@code null} si no se encuentra.
     */
    Ejemplar obtenerEjemplarPorId(long id);
    
    /**
     * Obtiene todos los ejemplares.
     *
     * @return Un conjunto con todos los ejemplares.
     */
    Set<Ejemplar> obtenerTodosEjemplares();
}
