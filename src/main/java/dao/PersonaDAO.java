package dao;

import java.util.Set;
import modelo.Persona;

/**
 * Interfaz para el manejo de operaciones CRUD sobre la entidad Persona.
 */
public interface PersonaDAO {
    int insertar(Persona persona);
    int eliminar(Persona persona);
    int modificar(Persona persona);
    
    Persona findById(Long id);
    Persona findByNombre(String nombre);
    Persona findByEmail(String email);
    
    Set<Persona> findAll();
}
