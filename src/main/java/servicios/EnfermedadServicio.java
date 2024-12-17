package servicios;

import modelo.Enfermedad;

public interface EnfermedadServicio {

	boolean agregar(Enfermedad enfermedad);

	Enfermedad findbyId(Long idEnfermedad);

	Enfermedad findByNombre(String nombre);


}
