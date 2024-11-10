package modelo;

import java.util.Date;

/**
 * Clase que representa un mensaje, que contiene un ID, fecha y hora, el contenido del mensaje, 
 * y las referencias a la persona y al ejemplar involucrados.
 */
public class Mensaje {

    private Long id;           // ID del mensaje
    private Date fechaHora;    // Fecha y hora en que se envió el mensaje
    private String mensaje;    // Contenido del mensaje
    private Long persona;      // ID de la persona que envió el mensaje
    private Long ejemplar;     // ID del ejemplar al que hace referencia el mensaje
    
    /**
     * Constructor por defecto de la clase Mensaje.
     */
    public Mensaje() {
        super();
    }

    /**
     * Constructor que inicializa todos los atributos del mensaje.
     * 
     * @param id          El ID del mensaje.
     * @param fechaActual La fecha y hora en que se envió el mensaje.
     * @param mensaje     El contenido del mensaje.
     * @param persona     El ID de la persona que envió el mensaje.
     * @param ejemplar    El ID del ejemplar al que hace referencia el mensaje.
     */
    public Mensaje(Long id, Date fechaActual, String mensaje, Long persona, Long ejemplar) {
        this.id = id;
        this.fechaHora = fechaActual;
        this.mensaje = mensaje;
        this.persona = persona;
        this.ejemplar = ejemplar;
    }

    /**
     * Obtiene el ID del mensaje.
     * 
     * @return El ID del mensaje.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del mensaje.
     * 
     * @param id El nuevo ID del mensaje.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora en que se envió el mensaje.
     * 
     * @return La fecha y hora en que se envió el mensaje.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora en que se envió el mensaje.
     * 
     * @param fechaHora La nueva fecha y hora del mensaje.
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el contenido del mensaje.
     * 
     * @return El contenido del mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el contenido del mensaje.
     * 
     * @param mensaje El nuevo contenido del mensaje.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el ID de la persona que envió el mensaje.
     * 
     * @return El ID de la persona que envió el mensaje.
     */
    public Long getPersona() {
        return persona;
    }

    /**
     * Establece el ID de la persona que envió el mensaje.
     * 
     * @param persona El nuevo ID de la persona que envió el mensaje.
     */
    public void setPersona(Long persona) {
        this.persona = persona;
    }

    /**
     * Obtiene el ID del ejemplar al que hace referencia el mensaje.
     * 
     * @return El ID del ejemplar al que hace referencia el mensaje.
     */
    public Long getEjemplar() {
        return ejemplar;
    }

    /**
     * Establece el ID del ejemplar al que hace referencia el mensaje.
     * 
     * @param ejemplar El nuevo ID del ejemplar al que hace referencia el mensaje.
     */
    public void setEjemplar(Long ejemplar) {
        this.ejemplar = ejemplar;
    }
}
