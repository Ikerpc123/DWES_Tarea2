package modelo;

public class Ejemplar {
    private Long id;
    private String nombre;
    private String idplanta;

    public Ejemplar(Long id, String nombre, String idplanta) {
        this.id = id;
        this.nombre = nombre;
        this.idplanta = idplanta;
    }
    
    public Ejemplar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdPlanta() {
        return idplanta;
    }

    public void setPlanta(String idplanta) {
        this.idplanta = idplanta;
    }
}
