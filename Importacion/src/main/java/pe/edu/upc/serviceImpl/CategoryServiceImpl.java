package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.entity.Category;
import pe.edu.upc.spring.repository.ICategoryRepository;
import pe.edu.upc.spring.service.ICategoryService;



@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository cR;

	@Override
	@Transactional
	public Integer insert(Category category) {
		int rpta = cR.buscarCategoria(category.getCategoryName());
		if (rpta == 0) {
			cR.save(category);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long idCategory) {
		cR.deleteById(idCategory);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> listId(long idCategory) {
		return cR.findById(idCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> list() {
		return cR.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findName(String categoryName) {
		return cR.buscarNombre(categoryName);
	}
}
