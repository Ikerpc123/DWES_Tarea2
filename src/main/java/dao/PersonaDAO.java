package dao;

import java.util.Set;

import modelo.Persona;

public interface PersonaDAO {
	
	int insertar(Persona persona);
    int eliminar(Persona persona);
    int modificar (Persona persona);

    Persona findById(Long id);
    Persona findByNombre(String nombre);

    Set<Persona> findAll();
	Persona findByEmail(String email);
}
