package servicios;

import modelo.Credenciales;

public interface CredencialServicio {
	boolean validar(String usuario, String password);
	 public boolean insertarCredenciales(Credenciales credenciales, String email);
}
