package servicios;

import modelo.Persona;

public interface PersonaServicio {
	boolean validar(String usuario, String password);
    boolean registrarPersona(Persona persona);
    public Persona buscarPorNombre(String nombre);
	Persona buscarPorId(Long id);
}
