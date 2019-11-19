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
	
	@Query(value = "SELECT b.brand_name,count(pr.id_producto) from public.importation ijoin import_details ide on  ide.id_importation = i.id_importation join product pr on ide.id_product = pr.id_producto join brand b on b.id_brand = pr.id_brand group by b.brand_name ORDER BY COUNT(b.brand_name) DESC limit 1", nativeQuery = true)
	public List<String[]> brandtop();

}
