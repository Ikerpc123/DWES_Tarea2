package servicioImpl;

import java.util.Set;

import dao.MensajeDAO;
import modelo.Mensaje;
import servicios.MensajeServicio;

public class MensajeServicioImpl implements MensajeServicio {

    private MensajeDAO mensajeDAO;

    public MensajeServicioImpl(MensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    @Override
    public boolean validarMensaje(Mensaje mensaje) {
    	
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isEmpty()) {
            System.out.println("Error: El mensaje no puede estar vacío.");
            return false;
        }
        if (mensaje.getPersona() == null || mensaje.getEjemplar() == null) {
            System.out.println("Error: El mensaje debe tener una persona y un ejemplar asignados.");
            return false;
        }
        return true;
    }

    @Override
    public boolean insertarMensaje(Mensaje mensaje) {
    	
        if (validarMensaje(mensaje)) {
            int resultado = mensajeDAO.insertar(mensaje);
            return resultado > 0;
        }
        return false;
    }
    
    @Override
    public Set<Mensaje> obtenerTodosMensajes(){
    	return mensajeDAO.findAll();
    }
}
