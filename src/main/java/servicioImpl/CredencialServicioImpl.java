package servicioImpl;

import dao.CredencialesDAO;
import modelo.Credenciales;
import servicios.CredencialServicio;

public class CredencialServicioImpl implements CredencialServicio {
    
    private CredencialesDAO credencialesDAO;

    public CredencialServicioImpl(CredencialesDAO credencialesDAO) {
        this.credencialesDAO = credencialesDAO;
    }

    @Override
    public boolean validar(String usuario, String password) {
        Credenciales credencial = credencialesDAO.findByUsuario(usuario);
        
        if (credencial != null && credencial.getPassword().equals(password)) {
            return true;
        }
        
        return false;
    }
}
