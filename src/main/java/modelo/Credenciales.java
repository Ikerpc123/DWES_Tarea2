package modelo;

public class Credenciales {
    private Long id;
    private String usuario;
    private String password;
    private Long persona;

    public Credenciales(Long id, String usuario, String password, Long persona) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }
}
