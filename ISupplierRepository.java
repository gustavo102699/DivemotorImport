package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long>{
	@Query("select count(l.supplierName) from Supplier l where l.supplierName=:supplierName")
	public int buscarSupplier(@Param("supplierName") String nombre);

	@Query("from Supplier p where p.supplierName like %:supplierName%")
	List<Supplier> buscarNombre(@Param("supplierName") String nombreSupplier);
	
	@Query(value = "select  b.supplier_name,count(pr.id_producto) from public.importation i join import_details ide on ide.id_importation = i.id_importation join product pr on ide.id_product = pr.id_producto join supplier b on b.id_supplier = pr.id_supplier group by b.supplier_name  ORDER BY COUNT(b.supplier_name) DESC limit 1", nativeQuery = true)
	public List<String[]> suppliertop();
}
