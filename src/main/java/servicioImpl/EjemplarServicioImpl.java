package servicioImpl;

import java.util.Set;

import dao.EjemplarDAO;
import modelo.Ejemplar;
import servicios.EjemplarServicio;

public class EjemplarServicioImpl implements EjemplarServicio {
    
    private final EjemplarDAO ejemplarDAO;

    public EjemplarServicioImpl(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
    }

    @Override
    public boolean agregarEjemplar(Ejemplar ejemplar) {
        if (validarEjemplar(ejemplar)) {
            int resultado = ejemplarDAO.insertar(ejemplar);
            return resultado > 0;
        }
        return false;
    }

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

    @Override
    public boolean modificarEjemplar(Ejemplar ejemplar) {
        if (validarEjemplar(ejemplar)) {
            int resultado = ejemplarDAO.modificar(ejemplar);
            return resultado > 0;
        }
        return false;
    }

    @Override
    public Ejemplar obtenerEjemplarPorId(long id) {
        if (id <= 0) {
            System.out.println("ID no válido");
            return null;
        }
        return ejemplarDAO.findById(id);
    }

    @Override
    public Set<Ejemplar> obtenerTodosEjemplares() {
        return ejemplarDAO.findAll();
    }

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
