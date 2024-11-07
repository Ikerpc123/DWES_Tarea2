package servicios;

import java.util.Set;

import modelo.Mensaje;

public interface MensajeServicio {
	boolean validarMensaje(Mensaje mensaje);
    boolean insertarMensaje(Mensaje mensaje);
    Set<Mensaje> obtenerTodosMensajes();
}
