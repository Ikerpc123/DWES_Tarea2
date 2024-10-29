package dao;

import java.util.Set;

import modelo.Ejemplar;

public interface EjemplarDAO {

	int insertar(Ejemplar ejemplar);
    int eliminar(Ejemplar ejemplar);
    int modificar (Ejemplar ejemplar);

    Ejemplar findById(long id);
    Ejemplar findByNombre(String nombre);

    Set<Ejemplar> findAll();
}
