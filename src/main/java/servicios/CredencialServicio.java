package servicios;

import modelo.Credenciales;

public interface CredencialServicio {
	boolean validar(String usuario, String password);
	 public boolean insertarCredenciales(Credenciales credenciales, String email);
	 public boolean esAdministrador(String usuario, String contra);
	 public Credenciales buscarPorUsuario(String nombre);
}
