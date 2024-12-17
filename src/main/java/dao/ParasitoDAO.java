package dao;

import java.util.List;

import modelo.Parasito;

public interface ParasitoDAO {

	int insertar(Parasito parasito);

	List<Parasito> mostrar();

	void update(Parasito parasito, Long idEnfermedad);

	Parasito findById(Long id);

}
