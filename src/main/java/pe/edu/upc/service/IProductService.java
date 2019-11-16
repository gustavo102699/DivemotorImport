package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Product;


public interface IProductService {

	public boolean insert(Product product);

	public boolean modificar(Product product);

	public void eliminar(long idProducto);

	Product listarId(long idProducto);

	List<Product> listar();
	
	Optional<Product> findById(Long idProduct);

	//List<Product> buscarCategoria(String categoryName);

	//List<Product> buscarMarca(String brandName);

	//List<Product> buscarProveedor(String supplierName);

	List<Product> findName(String productName);
	
	public List<String[]> prodXimp();

}
