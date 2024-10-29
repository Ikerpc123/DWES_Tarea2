package dao;

import modelo.Planta;

import java.util.Set;

public interface PlantaDAO {

    int insertar(Planta planta);
    int eliminar(Planta planta);
    int modificar (Planta planta);

    Planta findById(String idplanta);
    Planta findByNombre(String nombreComun);

    Set<Planta> findAll();
}
