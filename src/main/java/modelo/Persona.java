package modelo;

/**
 * Clase que representa una persona con un ID, nombre y dirección de correo electrónico.
 */
public class Persona {

    private Long id;      // ID único de la persona
    private String nombre; // Nombre de la persona
    private String email;  // Dirección de correo electrónico de la persona

    /**
     * Constructor que inicializa todos los atributos de la persona.
     * 
     * @param id      El ID de la persona.
     * @param nombre  El nombre de la persona.
     * @param email   La dirección de correo electrónico de la persona.
     */
    public Persona(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * Constructor que inicializa solo el nombre y correo electrónico de la persona.
     * 
     * @param nombre El nombre de la persona.
     * @param email  La dirección de correo electrónico de la persona.
     */
    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * Obtiene el ID de la persona.
     * 
     * @return El ID de la persona.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la persona.
     * 
     * @param id El nuevo ID de la persona.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     * 
     * @param nombre El nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección de correo electrónico de la persona.
     * 
     * @return La dirección de correo electrónico de la persona.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico de la persona.
     * 
     * @param email La nueva dirección de correo electrónico de la persona.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
