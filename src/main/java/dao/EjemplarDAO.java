package dao;

import java.util.Set;
import modelo.Ejemplar;

/**
 * Interface que define los métodos CRUD para manejar los ejemplares en la base de datos.
 */
public interface EjemplarDAO {

    /**
     * Inserta un nuevo ejemplar en la base de datos.
     *
     * @param ejemplar El objeto Ejemplar a insertar.
     * @return El número de filas afectadas (1 si se inserta correctamente, 0 en caso contrario).
     */
    int insertar(Ejemplar ejemplar);

    /**
     * Elimina un ejemplar de la base de datos.
     *
     * @param ejemplar El objeto Ejemplar a eliminar.
     * @return El número de filas afectadas (1 si se elimina correctamente, 0 en caso contrario).
     */
    int eliminar(Ejemplar ejemplar);

    /**
     * Modifica un ejemplar existente en la base de datos.
     *
     * @param ejemplar El objeto Ejemplar con los datos actualizados.
     * @return El número de filas afectadas (1 si se modifica correctamente, 0 en caso contrario).
     */
    int modificar(Ejemplar ejemplar);

    /**
     * Busca un ejemplar por su ID.
     *
     * @param id El ID del ejemplar a buscar.
     * @return El objeto Ejemplar correspondiente o null si no se encuentra.
     */
    Ejemplar findById(long id);

    /**
     * Busca un ejemplar por su nombre.
     *
     * @param nombre El nombre del ejemplar a buscar.
     * @return El objeto Ejemplar correspondiente o null si no se encuentra.
     */
    Ejemplar findByNombre(String nombre);

    /**
     * Obtiene todos los ejemplares de la base de datos.
     *
     * @return Un conjunto de objetos Ejemplar.
     */
    Set<Ejemplar> findAll();
}
