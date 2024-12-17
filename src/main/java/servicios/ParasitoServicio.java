package servicios;

import java.util.List;

import modelo.Parasito;

public interface ParasitoServicio {

	int insertar(Parasito parasito);

	List<Parasito> mostrar();

	void update(Parasito parasitoSeleccionado, Long idEnfermedad);

	Parasito findById(Long id);

}
