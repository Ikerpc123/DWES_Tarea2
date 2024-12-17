package servicioImpl;

import dao.EnfermedadDAO;
import modelo.Enfermedad;
import servicios.EnfermedadServicio;

public class EnfermedadServicioImpl implements EnfermedadServicio{

	private final EnfermedadDAO enfermedadDAO;

	public EnfermedadServicioImpl(EnfermedadDAO enfermedadDAO) {
		this.enfermedadDAO = enfermedadDAO;
	}

	public boolean validarEnfermedad(Enfermedad enfermedad) {
		return enfermedad != null && enfermedad.getNombre() != null && enfermedad.getSintomas() != null
				&& enfermedad.getCod_planta() != null;
	}
	
	@Override
	public boolean agregar(Enfermedad enfermedad) {
		if (validarEnfermedad(enfermedad)) {
			int resultado = enfermedadDAO.insertar(enfermedad);
			return resultado > 0;
		}
		System.out.println("Datos de la enfermedad no válidos");
		return false;
	}
	
	@Override
	public Enfermedad findbyId(Long idEnfermedad) {
		if (idEnfermedad != null) {
			return enfermedadDAO.findById(idEnfermedad);
		}
		System.out.println("Id de la enfermedad no válido");
		return null;
	}

	@Override
	public Enfermedad findByNombre(String nombre) {
		if (nombre != null) {
			return enfermedadDAO.findByNombre(nombre);
		}
		System.out.println("Nombre de la enfermedad no válido");
		return null;
	}
}
