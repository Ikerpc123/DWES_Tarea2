package servicioImpl;

import dao.PersonaDAO;
import modelo.Persona;
import servicios.PersonaServicio;

/**
 * Implementación del servicio de personas. Esta clase proporciona métodos para validar, registrar
 * y buscar personas en el sistema, ya sea por nombre o por ID.
 */
public class PersonaServicioImpl implements PersonaServicio {

    private final PersonaDAO personaDAO;

    /**
     * Constructor que inicializa el objeto DAO de personas.
     *
     * @param personaDAO El DAO de personas.
     */
    public PersonaServicioImpl(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    /**
     * Registra una nueva persona en el sistema. Antes de registrar, verifica que el nombre y el email
     * no estén duplicados en el sistema.
     *
     * @param persona La persona que se desea registrar.
     * @return {@code true} si la persona fue registrada correctamente, {@code false} si hubo un error
     *         o si los datos no son válidos (nombre o email duplicados).
     */
    public boolean registrarPersona(Persona persona) {
        // Verificar que el nombre no esté repetido
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

    /**
     * Busca una persona por su nombre.
     *
     * @param nombre El nombre de la persona a buscar.
     * @return La persona encontrada, o {@code null} si no se encuentra ninguna persona con ese nombre.
     */
    public Persona buscarPorNombre(String nombre) {
        return personaDAO.findByNombre(nombre);
    }

    /**
     * Método no implementado para la validación de un usuario y contraseña.
     *
     * @param usuario El nombre de usuario de la persona.
     * @param password La contraseña de la persona.
     * @return {@code false} ya que el método no está implementado.
     */
    @Override
    public boolean validar(String usuario, String password) {
        // TODO: Implementar la lógica de validación de credenciales
        return false;
    }

    /**
     * Busca una persona por su ID.
     *
     * @param id El ID de la persona a buscar.
     * @return La persona encontrada, o {@code null} si no se encuentra ninguna persona con ese ID.
     */
    @Override
    public Persona buscarPorId(Long id) {
        return personaDAO.findById(id);
    }
}
