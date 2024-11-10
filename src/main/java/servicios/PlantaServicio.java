package servicios;

import java.util.Set;
import modelo.Planta;

/**
 * Interfaz que define los servicios relacionados con las plantas.
 * Incluye métodos para agregar, eliminar, actualizar y buscar plantas,
 * así como para obtener todas las plantas almacenadas en el sistema.
 */
public interface PlantaServicio {
    
    /**
     * Agrega una nueva planta al sistema.
     *
     * @param planta La planta que se desea agregar.
     * @return {@code true} si la planta fue agregada correctamente, {@code false} en caso contrario.
     */
    boolean agregar(Planta planta);

    /**
     * Elimina una planta del sistema identificada por su código.
     *
     * @param codigo El código de la planta a eliminar.
     * @return {@code true} si la planta fue eliminada correctamente, {@code false} en caso contrario.
     */
    boolean eliminar(String codigo);

    /**
     * Actualiza los datos de una planta en el sistema.
     *
     * @param planta La planta con los nuevos datos.
     * @return {@code true} si la planta fue actualizada correctamente, {@code false} en caso contrario.
     */
    boolean actualizar(Planta planta);

    /**
     * Busca una planta en el sistema por su código.
     *
     * @param codigo El código de la planta a buscar.
     * @return La planta encontrada, o {@code null} si no se encuentra ninguna planta con ese código.
     */
    Planta findbyId(String codigo);

    /**
     * Busca una planta en el sistema por su nombre común.
     *
     * @param nombreComun El nombre común de la planta a buscar.
     * @return La planta encontrada, o {@code null} si no se encuentra ninguna planta con ese nombre común.
     */
    Planta findbyNombre(String nombreComun);

    /**
     * Obtiene todas las plantas almacenadas en el sistema.
     *
     * @return Un conjunto de todas las plantas en el sistema.
     */
    Set<Planta> obtenerTodas();
}
