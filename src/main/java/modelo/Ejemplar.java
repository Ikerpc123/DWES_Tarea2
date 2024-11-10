package modelo;

/**
 * Clase que representa un ejemplar de planta en el sistema, con atributos como su ID, nombre y el ID de la planta asociada.
 */
public class Ejemplar {
    
    private Long id;           // ID del ejemplar
    private String nombre;     // Nombre del ejemplar
    private String idplanta;   // ID de la planta asociada al ejemplar

    /**
     * Constructor que inicializa el ejemplar con todos los atributos.
     * 
     * @param id        El ID del ejemplar.
     * @param nombre    El nombre del ejemplar.
     * @param idplanta  El ID de la planta asociada al ejemplar.
     */
    public Ejemplar(Long id, String nombre, String idplanta) {
        this.id = id;
        this.nombre = nombre;
        this.idplanta = idplanta;
    }

    /**
     * Constructor por defecto para crear un ejemplar sin inicializar los atributos.
     */
    public Ejemplar() {
    }

    /**
     * Obtiene el ID del ejemplar.
     * 
     * @return El ID del ejemplar.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del ejemplar.
     * 
     * @param id El nuevo ID del ejemplar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ejemplar.
     * 
     * @return El nombre del ejemplar.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ejemplar.
     * 
     * @param nombre El nuevo nombre del ejemplar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID de la planta asociada al ejemplar.
     * 
     * @return El ID de la planta asociada.
     */
    public String getIdPlanta() {
        return idplanta;
    }

    /**
     * Establece el ID de la planta asociada al ejemplar.
     * 
     * @param idplanta El nuevo ID de la planta asociada.
     */
    public void setPlanta(String idplanta) {
        this.idplanta = idplanta;
    }
}
