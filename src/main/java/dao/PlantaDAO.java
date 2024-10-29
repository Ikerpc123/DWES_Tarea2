package DAO;

import modelo.Planta;

import java.util.Set;

public interface PlantaDAO {

    int insertar(Planta planta);
    int elimintar(Planta planta);
    int modificar (Planta planta);

    Planta findById(String idplanta);
    Planta findByNombre(String nombreComun);

    Set<Planta> findAll();
}
