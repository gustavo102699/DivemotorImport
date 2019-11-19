package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Category;
import pe.edu.upc.entity.ImportDetails;
import pe.edu.upc.repository.IImportDetailRepository;
import pe.edu.upc.service.IImportDetailService;

@Service
public class ImportDetailServiceImpl implements IImportDetailService{

	@Autowired
	private IImportDetailRepository ideR;
	


	@Override
	public void delete(Long idImportDetail) {
		ideR.deleteById(idImportDetail);
	}

	@Override
	public List<Category> findName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(ImportDetails impd) {
		ImportDetails impde = ideR.save(impd);
		if (impde == null) {
			return 0;
		} else {
			return 1;
		}
	}

	
}
