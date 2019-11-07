package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Cancion;

public interface ICancionService {
	public Integer insertar(Cancion cancion);

	public List<Cancion> listar();

	Optional<Cancion> listarId(int idCancion);

	public void eliminar(int idCancion);

	List<Cancion> buscarCancion(String nombreCancion);

	public List<Cancion> findByNombreCancionLikeIgnoreCase(String nombreCancion);
}
