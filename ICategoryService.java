package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.entity.Category;



public interface ICategoryService {

	public Integer insert(Category category);

	public void delete(long idCategory);

	Optional<Category> listId(long idCategory);

	List<Category> list();

	List<Category> findName(String categoryName);
}
