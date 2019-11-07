package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Cancion;

@Repository
public interface ICancionRepository extends JpaRepository<Cancion, Integer> {
	@Query("select count(c.nombreCancion) from Cancion c where c.nombreCancion=:nombreCancion")
	public int findByNombreCancion(@Param("nombreCancion") String nombreCancion);

	@Query("from Cancion c where c.nombreCancion like %:nombreCancion%")
	List<Cancion> buscarCancion(@Param("nombreCancion") String nombreCancion);

	List<Cancion> findByNombreCancionLikeIgnoreCase(String nombreCancion);
}
