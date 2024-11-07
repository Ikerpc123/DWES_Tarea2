package servicioImpl;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import modelo.Credenciales;
import modelo.Persona;
import servicios.CredencialServicio;

public class CredencialServicioImpl implements CredencialServicio {
    
	private CredencialesDAO credencialesDAO;
	private PersonaDAO personaDAO;

    public CredencialServicioImpl(CredencialesDAO credencialesDAO, PersonaDAO personaDAO) {
        this.credencialesDAO = credencialesDAO;
        this.personaDAO = personaDAO;
    }

    @Override
    public boolean validar(String usuario, String password) {
        Credenciales credencial = credencialesDAO.findByUsuario(usuario);
        
        return credencial != null && credencial.getPassword().equals(password);
    }

    // Método para insertar credenciales
    public boolean insertarCredenciales(Credenciales credenciales, String email) {
        Persona persona = personaDAO.findByEmail(email);
        credenciales.setPersona(persona.getId());
        int resultado = credencialesDAO.insertar(credenciales);
        return resultado > 0;
    }
    
 // Nuevo método para verificar si el usuario es administrador
    public boolean esAdministrador(String usuario, String password) {
        Credenciales credencial = credencialesDAO.findByUsuario(usuario);
        return credencial != null 
                && "admin".equals(credencial.getUsuario()) 
                && "admin".equals(credencial.getPassword());
    }
    
    @Override
    public Credenciales buscarPorUsuario(String nombre) {
    	Credenciales usuario = credencialesDAO.findByUsuario(nombre);
        return usuario;
    }
    
}
