package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.entity.Product;
import pe.edu.upc.spring.repository.IProductRepository;
import pe.edu.upc.spring.service.IProductService;


@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository pR;

	@Override
	@Transactional
	public boolean insert(Product product) {
		Product objProduct = pR.save(product);
		if (objProduct == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(Product product) {
		boolean flag = false;
		try {
			pR.save(product);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	public void eliminar(long idProducto) {
		pR.deleteById(idProducto);

	}

	@Override
	@Transactional(readOnly = true)
	public Product listarId(long idProducto) {
		Optional<Product> op = pR.findById(idProducto);
		return op.isPresent() ? op.get() : new Product();
	}

	@Override
	public List<Product> listar() {
		return pR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findName(String productName) {
		return pR.buscarProducto(productName);
	}


	//@Override
	//public List<Product> buscarCategoria(String categoryName) {
		//return pR.buscarCategoria(categoryName);
	//}

	//@Override
	//public List<Product> buscarMarca(String brandName) {
		// TODO Auto-generated method stub
		//return pR.buscarMarca(brandName);
	//}

	//@Override
	//public List<Product> buscarProveedor(String supplierName) {
		// TODO Auto-generated method stub
		//return pR.buscarProveedor(supplierName);
	//}

	

}
