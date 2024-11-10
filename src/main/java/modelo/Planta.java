package modelo;

/**
 * Clase que representa una planta con un código, nombre común y nombre científico.
 */
public class Planta {

    private String codigo;          // Código único de la planta
    private String nombreComun;     // Nombre común de la planta
    private String nombreCientifico; // Nombre científico de la planta

    /**
     * Constructor que inicializa todos los atributos de la planta.
     * 
     * @param codigo          El código único de la planta.
     * @param nombreComun     El nombre común de la planta.
     * @param nombreCientifico El nombre científico de la planta.
     */
    public Planta(String codigo, String nombreComun, String nombreCientifico) {
        this.codigo = codigo;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
    }

    /**
     * Obtiene el código de la planta.
     * 
     * @return El código único de la planta.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la planta.
     * 
     * @param codigo El nuevo código único de la planta.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre común de la planta.
     * 
     * @return El nombre común de la planta.
     */
    public String getNombreComun() {
        return nombreComun;
    }

    /**
     * Establece el nombre común de la planta.
     * 
     * @param nombreComun El nuevo nombre común de la planta.
     */
    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    /**
     * Obtiene el nombre científico de la planta.
     * 
     * @return El nombre científico de la planta.
     */
    public String getNombreCientifico() {
        return nombreCientifico;
    }

    /**
     * Establece el nombre científico de la planta.
     * 
     * @param nombreCientifico El nuevo nombre científico de la planta.
     */
    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }
}
