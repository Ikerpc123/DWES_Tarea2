package servicios;

import java.util.Set;

import modelo.Ejemplar;

public interface EjemplarServicio {
	boolean agregarEjemplar(Ejemplar ejemplar);
    boolean eliminarEjemplar(long id);
    boolean modificarEjemplar(Ejemplar ejemplar);
    Ejemplar obtenerEjemplarPorId(long id);
    Set<Ejemplar> obtenerTodosEjemplares();
}
