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
}
