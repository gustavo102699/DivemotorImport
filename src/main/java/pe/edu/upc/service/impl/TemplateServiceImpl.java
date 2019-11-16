package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.FileTemplate;
import pe.edu.upc.repository.ITemplateRepository;
import pe.edu.upc.service.ITemplateService;

@Service
public class TemplateServiceImpl implements ITemplateService{

	@Autowired
	private ITemplateRepository tR;
	
	@Override
	public void insert(FileTemplate templ) {
		tR.save(templ);
	}

	@Override
	public FileTemplate listId(long idTemplate) {
		
		Optional<FileTemplate> temp = tR.findById(idTemplate);
		return temp.isPresent() ? temp.get() : new FileTemplate();
	}

	@Override
	public List<FileTemplate> list() {
		// TODO Auto-generated method stub
		return tR.findAll(Sort.by(Sort.Direction.ASC, "templateName"));
	}

	@Override
	public List<FileTemplate> findName(String templateName) {
		// TODO Auto-generated method stub
		return tR.buscarNombre(templateName);
		
	}



}
