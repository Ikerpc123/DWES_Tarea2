package dao;

import modelo.Enfermedad;

public interface EnfermedadDAO {

	int insertar(Enfermedad enfermedad);

	Enfermedad findById(Long idEnfermedad);

	Enfermedad findByNombre(String nombre);

	
}
