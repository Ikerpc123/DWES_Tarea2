package servicioImpl;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import modelo.Credenciales;
import modelo.Persona;
import servicios.CredencialServicio;

/**
 * Implementación del servicio de credenciales.
 * Realiza operaciones como validar credenciales, insertar nuevas credenciales, 
 * verificar si un usuario es administrador y buscar credenciales por nombre de usuario.
 */
public class CredencialServicioImpl implements CredencialServicio {
    
    private CredencialesDAO credencialesDAO;
    private PersonaDAO personaDAO;

    /**
     * Constructor que inicializa los objetos de acceso a datos (DAOs) necesarios.
     *
     * @param credencialesDAO El DAO de credenciales.
     * @param personaDAO El DAO de personas.
     */
    public CredencialServicioImpl(CredencialesDAO credencialesDAO, PersonaDAO personaDAO) {
        this.credencialesDAO = credencialesDAO;
        this.personaDAO = personaDAO;
    }

    /**
     * Valida las credenciales de un usuario comparando su nombre de usuario y contraseña.
     *
     * @param usuario El nombre de usuario a validar.
     * @param password La contraseña a validar.
     * @return {@code true} si las credenciales son válidas, {@code false} si son incorrectas.
     */
    @Override
    public boolean validar(String usuario, String password) {
        Credenciales credencial = credencialesDAO.findByUsuario(usuario);
        
        // Retorna true si la credencial existe y la contraseña coincide.
        return credencial != null && credencial.getPassword().equals(password);
    }

    /**
     * Inserta un nuevo registro de credenciales asociadas a un usuario mediante su correo electrónico.
     *
     * @param credenciales Las credenciales a insertar.
     * @param email El correo electrónico del usuario al que se asignan las credenciales.
     * @return {@code true} si las credenciales se insertaron correctamente, {@code false} si no.
     */
    public boolean insertarCredenciales(Credenciales credenciales, String email) {
        // Busca la persona por su correo electrónico
        Persona persona = personaDAO.findByEmail(email);
        
        // Asocia el ID de la persona a las credenciales
        credenciales.setPersona(persona.getId());
        
        // Inserta las credenciales y retorna el resultado
        int resultado = credencialesDAO.insertar(credenciales);
        return resultado > 0;
    }

    /**
     * Verifica si el usuario es un administrador comparando las credenciales con valores predefinidos.
     *
     * @param usuario El nombre de usuario a verificar.
     * @param password La contraseña del usuario a verificar.
     * @return {@code true} si el usuario es un administrador, {@code false} en caso contrario.
     */
    public boolean esAdministrador(String usuario, String password) {
        Credenciales credencial = credencialesDAO.findByUsuario(usuario);
        
        // Verifica si el nombre de usuario y la contraseña coinciden con los valores "admin"
        return credencial != null 
                && "admin".equals(credencial.getUsuario()) 
                && "admin".equals(credencial.getPassword());
    }

    /**
     * Busca las credenciales asociadas a un usuario por su nombre de usuario.
     *
     * @param nombre El nombre de usuario a buscar.
     * @return Las credenciales asociadas al nombre de usuario, o {@code null} si no se encuentran.
     */
    @Override
    public Credenciales buscarPorUsuario(String nombre) {
        return credencialesDAO.findByUsuario(nombre);
    }
}
