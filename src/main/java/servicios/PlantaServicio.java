package servicios;

import java.util.Set;

import modelo.Planta;

public interface PlantaServicio {
	
	boolean agregar(Planta planta);
    boolean eliminar(String codigo);
    boolean actualizar(Planta planta);
    Planta findbyId(String codigo);
    Planta findbyNombre(String nombreComun);
    Set<Planta> obtenerTodas();
}
