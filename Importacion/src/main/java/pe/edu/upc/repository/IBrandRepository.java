package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Brand;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
	@Query("select count(l.brandName) from Brand l where l.brandName=:brandName")
	public int buscarMarca(@Param("brandName") String nombre);

	@Query("from Brand p where p.brandName like %:brandName%")
	List<Brand> buscarNombre(@Param("brandName") String nombreMarca);

}
