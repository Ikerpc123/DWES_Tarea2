package modelo;

/**
 * Clase que representa las credenciales de un usuario, incluyendo su ID, nombre de usuario, contraseña y una referencia a una persona.
 */
public class Credenciales {
    
    private Long id;           // ID de las credenciales
    private String usuario;    // Nombre de usuario
    private String password;   // Contraseña del usuario
    private Long persona;      // ID de la persona asociada a estas credenciales

    /**
     * Constructor que inicializa las credenciales con todos los atributos.
     * 
     * @param id        El ID de las credenciales.
     * @param usuario   El nombre de usuario.
     * @param password  La contraseña del usuario.
     * @param persona   El ID de la persona asociada a estas credenciales.
     */
    public Credenciales(Long id, String usuario, String password, Long persona) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.persona = persona;
    }

    /**
     * Constructor simplificado que solo requiere el nombre de usuario y la contraseña.
     * 
     * @param usuario   El nombre de usuario.
     * @param password  La contraseña del usuario.
     */
    public Credenciales(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Obtiene el ID de las credenciales.
     * 
     * @return El ID de las credenciales.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de las credenciales.
     * 
     * @param id El nuevo ID de las credenciales.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param usuario El nuevo nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña asociada al usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña para el usuario.
     * 
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el ID de la persona asociada a estas credenciales.
     * 
     * @return El ID de la persona.
     */
    public Long getPersona() {
        return persona;
    }

    /**
     * Establece el ID de la persona asociada a estas credenciales.
     * 
     * @param persona El nuevo ID de la persona.
     */
    public void setPersona(Long persona) {
        this.persona = persona;
    }
}
