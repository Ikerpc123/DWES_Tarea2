package servicios;

import java.util.List;
import java.util.Set;

import modelo.Mensaje;

public interface MensajeServicio {
	boolean validarMensaje(Mensaje mensaje);
    boolean insertarMensaje(Mensaje mensaje);
    Set<Mensaje> obtenerTodosMensajes();
	Set<Mensaje> obtenerMensajesPorEjemplar(long idEjemplar);
	Set<Mensaje> filtrarMensajesPorPersona(long idPersona);
	Set<Mensaje> filtrarMensajesPorRangoFechas(String fechaInicio, String fechaFin);
	Set<Mensaje> filtrarMensajesPorTipoPlanta(String codigoPlanta);
}
