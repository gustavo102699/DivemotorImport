package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Genero;

public interface IGeneroService {
	public Integer insertar(Genero genero);

	public List<Genero> listar();

	Optional<Genero> listarId(int idGenero);

	public void eliminar(int idGenero);

	List<Genero> buscarGenero(String nombreGenero);

	public List<Genero> findByNombreGeneroLikeIgnoreCase(String nombreGenero);
}
