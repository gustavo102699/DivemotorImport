	package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Genero;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer> {

	@Query("select count(g.nombreGenero) from Genero g where g.nombreGenero=:nombreGenero")
	public int findByNombreGenero(@Param("nombreGenero") String nombreGenero);

	@Query("from Genero g where g.nombreGenero like %:nombreGenero%")
	List<Genero> buscarGenero(@Param("nombreGenero") String nombreGenero);

	List<Genero> findByNombreGeneroLikeIgnoreCase(String nombreGenero);
}
