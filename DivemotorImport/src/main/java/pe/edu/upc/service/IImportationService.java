package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Importation;

public interface IImportationService {

	public boolean insert(Importation importation);

	public boolean modificar(Importation importation);

	public void eliminar(Long idImport);

	Importation listarId(Long idImport);

	List<Importation> listar();
	
	Optional<Importation> fetchByImportIdWhithImportDetailsWithProduct(Long id);
	
	Optional<Importation> fetchByImportIdWhithLegalFilesWithFileTemplate(Long id);

}
