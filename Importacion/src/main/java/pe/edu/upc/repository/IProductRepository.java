package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	@Query("select count(l.productName) from Product l where l.productName=:productName")
	public int buscarProducto(@Param("productName") String nombre);

	@Query("from Product p where p.productName like %:productName%")
	List<Product> buscarNombre(@Param("productName") String nombreProducto);

	@Query("select p from Product p where p.category.categoryName like %?1%")
	public List<Product> findProductByNameCategory(String nombreCategoria);
}
