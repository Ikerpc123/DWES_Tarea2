package servicioImpl;

import java.util.List;

import dao.ParasitoDAO;
import modelo.Parasito;
import servicios.ParasitoServicio;

public class ParasitoServicioImpl implements ParasitoServicio{
	
	private ParasitoDAO parasitoDAO;

	public ParasitoServicioImpl(ParasitoDAO parasitoDAO) {
		this.parasitoDAO = parasitoDAO;
	}

	@Override
	public int insertar(Parasito parasito) {
		return parasitoDAO.insertar(parasito);
	}
	
	@Override
	public List<Parasito> mostrar() {
		return parasitoDAO.mostrar();
	}
	
	@Override
	public Parasito findById(Long id) {
		return parasitoDAO.findById(id);
	}
	
	@Override
	public void update(Parasito parasitoSeleccionado, Long idEnfermedad) {
		parasitoDAO.update(parasitoSeleccionado, idEnfermedad);
	}

}
