package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Brand;
import pe.edu.upc.repository.IBrandRepository;
import pe.edu.upc.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandRepository bR;

	@Override
	@Transactional
	public Integer insert(Brand brand) {
		int rpta = bR.buscarMarca(brand.getBrandName());
		if (rpta == 0) {
			bR.save(brand);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long idBrand) {
		bR.deleteById(idBrand);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Brand> listId(long idBrand) {
		return bR.findById(idBrand);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Brand> list() {
		return bR.findAll(Sort.by(Sort.Direction.ASC, "idBrand"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Brand> findName(String brandName) {
		return bR.buscarNombre(brandName);
	}

}
