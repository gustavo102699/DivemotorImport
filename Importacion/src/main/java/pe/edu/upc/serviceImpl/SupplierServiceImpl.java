package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.entity.Supplier;
import pe.edu.upc.spring.repository.ISupplierRepository;
import pe.edu.upc.spring.service.ISupplierService;



@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierRepository sR;

	@Override
	public Integer insert(Supplier supplier) {
		int rpta = sR.buscarSupplier(supplier.getSupplierName());
		if (rpta == 0) {
			sR.save(supplier);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long idSupplier) {
		sR.deleteById(idSupplier);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Supplier> listId(long idSupplier) {
		return sR.findById(idSupplier);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Supplier> list() {
		return sR.findAll(Sort.by(Sort.Direction.ASC, "supplierName"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Supplier> findName(String supplierName) {
		return sR.buscarNombre(supplierName);
	}
}
