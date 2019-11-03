package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.Product;



@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select count(l.productName) from Product l where l.productName=:productName")
	public int buscarNombre(@Param("productName") String nombre);
	
	@Query("from Product p where p.productName like %:productName%")
	List<Product> buscarProducto(@Param("productName") String categoryName);

}
