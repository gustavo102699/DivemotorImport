package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.FileTemplate;


public interface ITemplateService {

	public void insert(FileTemplate temp);

	FileTemplate listId(long idTemplate);

	List<FileTemplate> list();

	List<FileTemplate> findName(String templateName);
}
