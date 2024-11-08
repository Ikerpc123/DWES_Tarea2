package dao;

import java.util.Set;

import modelo.Credenciales;

public interface CredencialesDAO {
	int insertar(Credenciales credencial);
    int eliminar(Credenciales credencial);
    int modificar(Credenciales credencial);

    Credenciales findById(Long id);
    Credenciales findByUsuario(String nombre);

    Set<Credenciales> findAll();
}
