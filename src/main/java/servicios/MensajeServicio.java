package servicios;

import modelo.Mensaje;

public interface MensajeServicio {
	boolean validarMensaje(Mensaje mensaje);
    boolean insertarMensaje(Mensaje mensaje);
}
