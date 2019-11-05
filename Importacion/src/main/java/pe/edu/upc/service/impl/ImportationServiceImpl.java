package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Importation;
import pe.edu.upc.repository.IImportationRepository;
import pe.edu.upc.service.IImportationService;

@Service
public class ImportationServiceImpl implements IImportationService {

	@Autowired
	private IImportationRepository iR;

	@Override
	public boolean insert(Importation importation) {
		Importation imp = iR.save(importation);
		if (imp == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean modificar(Importation importation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminar(long idImport) {
		// TODO Auto-generated method stub

	}

	@Override
	public Importation listarId(long idImport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Importation> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Importation> fetchByImportIdWhithImportDetailsWithProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Importation> fetchByImportIdWhithLegalFilesWithFileTemplate(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
