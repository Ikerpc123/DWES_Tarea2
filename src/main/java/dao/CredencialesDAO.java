package dao;

import java.util.Set;
import modelo.Credenciales;

/**
 * Interface que define los métodos CRUD para manejar credenciales en la base de datos.
 */
public interface CredencialesDAO {

    /**
     * Inserta una nueva credencial en la base de datos.
     *
     * @param credencial El objeto Credenciales a insertar.
     * @return El número de filas afectadas (1 si se inserta correctamente, 0 en caso contrario).
     */
    int insertar(Credenciales credencial);

    /**
     * Elimina una credencial de la base de datos.
     *
     * @param credencial El objeto Credenciales a eliminar.
     * @return El número de filas afectadas (1 si se elimina correctamente, 0 en caso contrario).
     */
    int eliminar(Credenciales credencial);

    /**
     * Modifica una credencial existente en la base de datos.
     *
     * @param credencial El objeto Credenciales con los nuevos datos a actualizar.
     * @return El número de filas afectadas (1 si se modifica correctamente, 0 en caso contrario).
     */
    int modificar(Credenciales credencial);

    /**
     * Busca una credencial por su ID.
     *
     * @param id El ID de la credencial a buscar.
     * @return El objeto Credenciales correspondiente o null si no se encuentra.
     */
    Credenciales findById(Long id);

    /**
     * Busca una credencial por su nombre de usuario.
     *
     * @param nombre El nombre de usuario de la credencial a buscar.
     * @return El objeto Credenciales correspondiente o null si no se encuentra.
     */
    Credenciales findByUsuario(String nombre);

    /**
     * Obtiene todas las credenciales de la base de datos.
     *
     * @return Un conjunto de objetos Credenciales.
     */
    Set<Credenciales> findAll();
}
