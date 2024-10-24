package patrones;

import DAO.PlantaDAO;
import modelo.Planta;

import java.sql.Connection;
import java.util.Set;

public class PLantaDAOImpl implements PlantaDAO {

    private Connection con;

    public PLantaDAOImpl (Connection con){

    }

    @Override
    public int insertar(Planta planta) {
        return 0;
    }

    @Override
    public int elimintar(Planta planta) {
        return 0;
    }

    @Override
    public int modificar(Planta planta) {
        return 0;
    }

    @Override
    public Planta findById(String idplanta) {
        return null;
    }

    @Override
    public Planta findByNombre(String nombreComun) {
        return null;
    }

    @Override
    public Set<Planta> findAll() {
        return Set.of();
    }
}
