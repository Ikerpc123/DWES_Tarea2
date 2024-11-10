package servicios;

import modelo.Persona;

/**
 * Interfaz que define los servicios relacionados con las personas.
 * Incluye métodos para validar personas, registrar una nueva persona
 * y buscar personas por nombre o por ID.
 */
public interface PersonaServicio {

    /**
     * Valida las credenciales de una persona, en función del usuario y la contraseña proporcionados.
     * Este método debe ser implementado en una clase que gestione la autenticación de personas.
     *
     * @param usuario El nombre de usuario de la persona.
     * @param password La contraseña de la persona.
     * @return {@code true} si las credenciales son válidas, {@code false} en caso contrario.
     */
    boolean validar(String usuario, String password);

    /**
     * Registra una nueva persona en el sistema.
     *
     * @param persona La persona que se desea registrar.
     * @return {@code true} si la persona fue registrada correctamente, {@code false} si hubo un error o
     *         si los datos no son válidos (por ejemplo, nombre o email duplicados).
     */
    boolean registrarPersona(Persona persona);

    /**
     * Busca una persona en el sistema por su nombre.
     *
     * @param nombre El nombre de la persona a buscar.
     * @return La persona encontrada, o {@code null} si no se encuentra ninguna persona con ese nombre.
     */
    Persona buscarPorNombre(String nombre);

    /**
     * Busca una persona en el sistema por su ID.
     *
     * @param id El ID de la persona a buscar.
     * @return La persona encontrada, o {@code null} si no se encuentra ninguna persona con ese ID.
     */
    Persona buscarPorId(Long id);
}
