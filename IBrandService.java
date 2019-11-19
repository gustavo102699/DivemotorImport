package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Brand;

public interface IBrandService {

	public Integer insert(Brand brand);

	public void delete(long idBrand);

	Optional<Brand> listId(long idBrand);

	List<Brand> list();

	List<Brand> findName(String brandName);
	
	public List<String[]> brandtop();
}
