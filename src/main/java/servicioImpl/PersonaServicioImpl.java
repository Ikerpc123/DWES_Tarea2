package servicioImpl;

import java.util.Set;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import dao.PlantaDAO;
import modelo.Credenciales;
import modelo.Persona;
import modelo.Planta;
import servicios.CredencialServicio;
import servicios.PersonaServicio;

public class PersonaServicioImpl implements PersonaServicio {

	private final PersonaDAO personaDAO;

    public PersonaServicioImpl(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }


    public boolean registrarPersona(Persona persona) {
        // Verificar que el email no esté repetido
        if (personaDAO.findByNombre(persona.getNombre()) != null) {
            System.out.println("Error: el nombre ya está registrado en el sistema.");
            return false;
        }
        
        // Verificar si el email ya existe
        if (personaDAO.findAll().stream().anyMatch(p -> p.getEmail().equals(persona.getEmail()))) {
            System.out.println("Error: el email ya está registrado en el sistema.");
            return false;
        }

        // Insertar la persona en la base de datos
        long resultadoPersona = personaDAO.insertar(persona);
        if (resultadoPersona > 0) {
            return true;
        } else {
            System.out.println("Error al registrar la persona.");
        }
        return false;
    }
    
 // Método que verifica si ya existe una persona con el mismo nombre
    public Persona buscarPorNombre(String nombre) {
        Persona personaExistente = personaDAO.findByNombre(nombre);
        return personaExistente;
    }

	@Override
	public boolean validar(String usuario, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
