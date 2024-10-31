package servicioImpl;

import java.util.Set;

import dao.PlantaDAO;
import modelo.Planta;
import servicios.PlantaServicio;

public class PlantaServicioImpl implements PlantaServicio {
	
	private final PlantaDAO plantaDAO;

    public PlantaServicioImpl(PlantaDAO plantaDAO) {
        this.plantaDAO = plantaDAO;
    }

    @Override
    public boolean agregar(Planta planta) {
        if (validarPlanta(planta)) {
            int resultado = plantaDAO.insertar(planta);
            return resultado > 0;
        }
        System.out.println("Datos de la planta no válidos");
        return false;
    }

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

    @Override
    public boolean actualizar(Planta planta) {
        if (validarPlanta(planta)) {
            int resultado = plantaDAO.modificar(planta);
            return resultado > 0;
        }
        System.out.println("Datos de la planta no válidos");
        return false;
    }

    @Override
    public Planta findbyId(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            System.out.println("Código no válido");
            return null;
        }
        return plantaDAO.findById(codigo);
    }

    @Override
    public Planta findbyNombre(String nombreComun) {
        if (nombreComun == null || nombreComun.isEmpty()) {
            System.out.println("Nombre común no válido");
            return null;
        }
        return plantaDAO.findByNombre(nombreComun);
    }

    @Override
    public Set<Planta> obtenerTodas() {
        return plantaDAO.findAll();
    }

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
