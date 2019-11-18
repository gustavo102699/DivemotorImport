package pe.edu.upc.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.LegalFiles;
import pe.edu.upc.repository.ILegalFileRepository;
import pe.edu.upc.service.ILegalFileService;

@Service
public class LegalFilesServiceImpl implements ILegalFileService {

	@Autowired
	private ILegalFileRepository lR;

	@Override
	public boolean modificar(LegalFiles file) {
		boolean flag = false;
		try {
			lR.save(file);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public void eliminar(Long idFile) {
		lR.deleteById(idFile);
	}

	@Override
	public LegalFiles listarId(Long idFile) {
		Optional<LegalFiles> op = lR.findById(idFile);
		return op.isPresent() ? op.get() : new LegalFiles();
	}

}
