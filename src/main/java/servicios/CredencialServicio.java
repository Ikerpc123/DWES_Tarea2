package servicios;

import modelo.Credenciales;

/**
 * Interfaz que define los métodos relacionados con los servicios de credenciales.
 * Proporciona operaciones para validar credenciales, insertar nuevas credenciales,
 * verificar si un usuario es administrador y buscar credenciales por nombre de usuario.
 */
public interface CredencialServicio {
    
    /**
     * Valida si el nombre de usuario y la contraseña coinciden con una entrada de credenciales.
     *
     * @param usuario El nombre de usuario a validar.
     * @param password La contraseña a validar.
     * @return {@code true} si las credenciales son válidas, {@code false} en caso contrario.
     */
    boolean validar(String usuario, String password);
    
    /**
     * Inserta nuevas credenciales asociadas a un usuario por su correo electrónico.
     *
     * @param credenciales Las credenciales a insertar.
     * @param email El correo electrónico del usuario al que se le asignan las credenciales.
     * @return {@code true} si las credenciales fueron insertadas correctamente, {@code false} en caso contrario.
     */
    public boolean insertarCredenciales(Credenciales credenciales, String email);
    
    /**
     * Verifica si un usuario tiene privilegios de administrador.
     *
     * @param usuario El nombre de usuario a verificar.
     * @param contra La contraseña del usuario a verificar.
     * @return {@code true} si el usuario es administrador, {@code false} en caso contrario.
     */
    public boolean esAdministrador(String usuario, String contra);
    
    /**
     * Busca las credenciales asociadas a un usuario por su nombre de usuario.
     *
     * @param nombre El nombre del usuario a buscar.
     * @return Las credenciales del usuario, o {@code null} si no se encuentran.
     */
    public Credenciales buscarPorUsuario(String nombre);
}
