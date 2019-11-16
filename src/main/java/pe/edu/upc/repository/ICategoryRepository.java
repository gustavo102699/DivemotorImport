package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
	@Query("select count(l.categoryName) from Category l where l.categoryName=:categoryName")
	public int buscarCategoria(@Param("categoryName") String nombre);

	@Query("from Category p where p.categoryName like %:categoryName%")
	List<Category> buscarNombre(@Param("categoryName") String nombreCategoria);
}
