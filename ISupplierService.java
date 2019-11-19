package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Supplier;



public interface ISupplierService {

	public Integer insert(Supplier supplier);

	public void delete(long idSupplier);

	Optional<Supplier> listId(long idSupplier);

	List<Supplier> list();

	List<Supplier> findName(String supplierName);
	
	public List<String[]> supplierXimp();
}
