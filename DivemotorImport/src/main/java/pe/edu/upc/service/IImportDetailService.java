package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Category;
import pe.edu.upc.entity.ImportDetails;

public interface IImportDetailService {
	public Integer insert(  ImportDetails impd);

	public void delete(Long idImportDetail);
	
	List<Category> findName(String productName);
}
