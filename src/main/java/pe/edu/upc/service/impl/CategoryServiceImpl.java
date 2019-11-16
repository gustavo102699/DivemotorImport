package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Category;
import pe.edu.upc.repository.ICategoryRepository;
import pe.edu.upc.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepository cR;

	@Transactional
	@Override
	public Integer insert(Category category) {
		int rpta = cR.buscarCategoria(category.getCategoryName());
		if(rpta==0)
		{
			cR.save(category);
		}
		return rpta;
	}

	@Transactional
	@Override
	public void delete(long idCategory) {
		cR.deleteById(idCategory);		
	}

	@Override
	public Optional<Category> listId(long idCategory) {
		return cR.findById(idCategory);
	}

	@Override
	public List<Category> list() {
		return cR.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
	}

	@Override
	public List<Category> findName(String categoryName) {
		return cR.buscarNombre(categoryName);
	}
	


}
