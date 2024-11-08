package dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import modelo.Mensaje;

public interface MensajeDAO {

	int insertar(Mensaje mensaje);
    int eliminar(Mensaje mensaje);
    int modificar (Mensaje mensaje);

    Mensaje findById(long id);
    Mensaje findByFecha(Date fecha);

    Set<Mensaje> findAll();
	Set<Mensaje> findMensajesByEjemplarId(long idEjemplar);
	Set<Mensaje> findByPersona(long idPersona);
	Set<Mensaje> findByFechaRango(String fechaInicio, String fechaFin);
	Set<Mensaje> findByPlanta(String codigoPlanta);
}
