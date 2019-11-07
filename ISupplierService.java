package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.entity.Supplier;



public interface ISupplierService {

	public Integer insert(Supplier supplier);

	public void delete(long idSupplier);

	Optional<Supplier> listId(long idSupplier);

	List<Supplier> list();

	List<Supplier> findName(String supplierName);
}
