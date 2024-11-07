package dao;

import java.util.Date;
import java.util.Set;

import modelo.Mensaje;

public interface MensajeDAO {

	int insertar(Mensaje mensaje);
    int eliminar(Mensaje mensaje);
    int modificar (Mensaje mensaje);

    Mensaje findById(long id);
    Mensaje findByFecha(Date fecha);

    Set<Mensaje> findAll();
}
