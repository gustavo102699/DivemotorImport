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
	public int buscarNombre(@Param("productName") String nombre);

	@Query("from Product p where p.productName like %:productName%")
	public List<Product> buscarProducto(@Param("productName") String nombreProducto);

	@Query("select p from Product p where p.category.categoryName like %?1%")
	public List<Product> findProductByNameCategory(String nombreCategoria);
	
	@Query( value="SELECT pr.product_name,sum(ide.quantity) from importation i join import_details ide on  ide.id_importation = i.id_importation join product pr on ide.id_product = pr.id_producto group by pr.product_name",
			nativeQuery = true )
	public List<String[]> prodXimp();
}
