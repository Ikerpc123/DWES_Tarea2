package servicioImpl;

import java.util.Set;
import dao.PlantaDAO;
import modelo.Planta;
import servicios.PlantaServicio;

/**
 * Implementación del servicio de plantas. Esta clase proporciona métodos para agregar, eliminar, actualizar
 * y buscar plantas en el sistema, así como para obtener todas las plantas.
 */
public class PlantaServicioImpl implements PlantaServicio {
    
    private final PlantaDAO plantaDAO;

    /**
     * Constructor que inicializa el objeto DAO de plantas.
     *
     * @param plantaDAO El DAO de plantas utilizado para interactuar con la base de datos.
     */
    public PlantaServicioImpl(PlantaDAO plantaDAO) {
        this.plantaDAO = plantaDAO;
    }

    /**
     * Agrega una nueva planta al sistema. Primero valida los datos de la planta.
     *
     * @param planta La planta que se desea agregar.
     * @return {@code true} si la planta fue agregada correctamente, {@code false} si los datos no son válidos.
     */
    @Override
    public boolean agregar(Planta planta) {
        if (validarPlanta(planta)) {
            int resultado = plantaDAO.insertar(planta);
            return resultado > 0;
        }
        System.out.println("Datos de la planta no válidos");
        return false;
    }

    /**
     * Elimina una planta del sistema, identificada por su código.
     *
     * @param codigo El código de la planta a eliminar.
     * @return {@code true} si la planta fue eliminada correctamente, {@code false} si el código es inválido
     *         o la planta no se encuentra.
     */
    @Override
    public boolean eliminar(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            System.out.println("Código no válido");
            return false;
        }
        Planta planta = plantaDAO.findById(codigo);
        if (planta == null) {
            System.out.println("Planta no encontrada");
            return false;
        }
        int resultado = plantaDAO.eliminar(planta);
        return resultado > 0;
    }

    /**
     * Actualiza los datos de una planta en el sistema.
     *
     * @param planta La planta con los nuevos datos a actualizar.
     * @return {@code true} si la planta fue actualizada correctamente, {@code false} si los datos no son válidos.
     */
    @Override
    public boolean actualizar(Planta planta) {
        if (validarPlanta(planta)) {
            int resultado = plantaDAO.modificar(planta);
            return resultado > 0;
        }
        System.out.println("Datos de la planta no válidos");
        return false;
    }

    /**
     * Busca una planta en el sistema por su código.
     *
     * @param codigo El código de la planta a buscar.
     * @return La planta encontrada, o {@code null} si no se encuentra ninguna planta con ese código.
     */
    @Override
    public Planta findbyId(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            System.out.println("Código no válido");
            return null;
        }
        return plantaDAO.findById(codigo);
    }

    /**
     * Busca una planta en el sistema por su nombre común.
     *
     * @param nombreComun El nombre común de la planta a buscar.
     * @return La planta encontrada, o {@code null} si no se encuentra ninguna planta con ese nombre común.
     */
    @Override
    public Planta findbyNombre(String nombreComun) {
        if (nombreComun == null || nombreComun.isEmpty()) {
            System.out.println("Nombre común no válido");
            return null;
        }
        return plantaDAO.findByNombre(nombreComun);
    }

    /**
     * Obtiene todas las plantas almacenadas en el sistema.
     *
     * @return Un conjunto de todas las plantas en el sistema.
     */
    @Override
    public Set<Planta> obtenerTodas() {
        return plantaDAO.findAll();
    }

    /**
     * Valida los datos de una planta antes de realizar cualquier operación con ella.
     * Verifica que la planta no sea nula y que los campos como código, nombre común y nombre científico
     * sean válidos (no nulos ni vacíos).
     *
     * @param planta La planta a validar.
     * @return {@code true} si los datos de la planta son válidos, {@code false} en caso contrario.
     */
    private boolean validarPlanta(Planta planta) {
        if (planta == null) {
            System.out.println("La planta no puede ser nula");
            return false;
        }
        if (planta.getCodigo() == null || planta.getCodigo().isEmpty()) {
            System.out.println("Código de planta no válido");
            return false;
        }
        if (planta.getNombreComun() == null || planta.getNombreComun().isEmpty()) {
            System.out.println("Nombre común de la planta no válido");
            return false;
        }
        if (planta.getNombreCientifico() == null || planta.getNombreCientifico().isEmpty()) {
            System.out.println("Nombre científico de la planta no válido");
            return false;
        }
        return true;
    }
}
