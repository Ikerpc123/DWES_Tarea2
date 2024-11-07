package modelo;

import java.util.Date;

public class Mensaje {
    private Long id;
    private Date fechaHora;
    private String mensaje;
    private Long persona;
    private Long ejemplar;
    
    public Mensaje() {
        super();
    }

    public Mensaje(Long id, Date fechaActual, String mensaje, Long persona, Long ejemplar) {
        this.id = id;
        this.fechaHora = fechaActual;
        this.mensaje = mensaje;
        this.persona = persona;
        this.ejemplar = ejemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public Long getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Long ejemplar) {
        this.ejemplar = ejemplar;
    }
}
