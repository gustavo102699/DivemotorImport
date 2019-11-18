package pe.edu.upc.service;

import pe.edu.upc.entity.LegalFiles;

public interface ILegalFileService {

	public boolean modificar(LegalFiles file);

	public void eliminar(Long idLegalFile);

	LegalFiles listarId(Long idLegalFile);
}
