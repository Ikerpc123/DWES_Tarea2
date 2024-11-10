package servicioImpl;

import java.util.Set;

import dao.EjemplarDAO;
import modelo.Ejemplar;
import servicios.EjemplarServicio;

/**
 * Implementación del servicio de ejemplares.
 * Realiza operaciones como agregar, eliminar, modificar ejemplares,
 * obtener ejemplares por ID y obtener todos los ejemplares.
 */
public class EjemplarServicioImpl implements EjemplarServicio {
    
    private final EjemplarDAO ejemplarDAO;

    /**
     * Constructor que inicializa el objeto DAO de ejemplares.
     *
     * @param ejemplarDAO El DAO de ejemplares.
     */
    public EjemplarServicioImpl(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
    }

    /**
     * Agrega un nuevo ejemplar si es válido.
     *
     * @param ejemplar El ejemplar a agregar.
     * @return {@code true} si el ejemplar se agrega correctamente, {@code false} si el ejemplar no es válido.
     */
    @Override
    public boolean agregarEjemplar(Ejemplar ejemplar) {
        // Valida el ejemplar antes de agregarlo
        if (validarEjemplar(ejemplar)) {
            int resultado = ejemplarDAO.insertar(ejemplar);
            return resultado > 0;
        }
        return false;
    }

    /**
     * Elimina un ejemplar mediante su ID.
     *
     * @param id El ID del ejemplar a eliminar.
     * @return {@code true} si el ejemplar fue eliminado correctamente, {@code false} si el ID es inválido o no se encuentra el ejemplar.
     */
    @Override
    public boolean eliminarEjemplar(long id) {
        if (id <= 0) {
            System.out.println("ID no válido");
            return false;
        }
        Ejemplar ejemplar = ejemplarDAO.findById(id);
        if (ejemplar == null) {
            System.out.println("Ejemplar no encontrado");
            return false;
        }
        int resultado = ejemplarDAO.eliminar(ejemplar);
        return resultado > 0;
    }

    /**
     * Modifica un ejemplar si es válido.
     *
     * @param ejemplar El ejemplar con los datos modificados.
     * @return {@code true} si el ejemplar fue modificado correctamente, {@code false} si el ejemplar no es válido.
     */
    @Override
    public boolean modificarEjemplar(Ejemplar ejemplar) {
        if (validarEjemplar(ejemplar)) {
            int resultado = ejemplarDAO.modificar(ejemplar);
            return resultado > 0;
        }
        return false;
    }

    /**
     * Obtiene un ejemplar por su ID.
     *
     * @param id El ID del ejemplar a obtener.
     * @return El ejemplar correspondiente al ID, o {@code null} si el ID es inválido.
     */
    @Override
    public Ejemplar obtenerEjemplarPorId(long id) {
        if (id <= 0) {
            System.out.println("ID no válido");
            return null;
        }
        return ejemplarDAO.findById(id);
    }

    /**
     * Obtiene todos los ejemplares disponibles.
     *
     * @return Un conjunto con todos los ejemplares.
     */
    @Override
    public Set<Ejemplar> obtenerTodosEjemplares() {
        return ejemplarDAO.findAll();
    }

    /**
     * Valida si un ejemplar es válido, verificando que no sea nulo y que tenga un ID de planta válido.
     *
     * @param ejemplar El ejemplar a validar.
     * @return {@code true} si el ejemplar es válido, {@code false} en caso contrario.
     */
    private boolean validarEjemplar(Ejemplar ejemplar) {
        if (ejemplar == null) {
            System.out.println("El ejemplar no puede ser nulo");
            return false;
        }
        if (ejemplar.getIdPlanta() == null || ejemplar.getIdPlanta().isEmpty()) {
            System.out.println("ID de planta no válido");
            return false;
        }
        return true;
    }
}
