package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Genero;
import pe.edu.upc.repository.IGeneroRepository;
import pe.edu.upc.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService {

	@Autowired
	private IGeneroRepository gR;

	@Override
	public Integer insertar(Genero genero) {
		int rpta = gR.findByNombreGenero(genero.getNombreGenero());
		if (rpta == 0) {
			gR.save(genero);
		}
		return rpta;
	}

	@Override
	public List<Genero> listar() {
		return gR.findAll();
	}

	@Override
	public Optional<Genero> listarId(int idGenero) {
		return gR.findById(idGenero);
	}

	@Override
	public void eliminar(int idGenero) {
		gR.deleteById(idGenero);

	}

	@Override
	public List<Genero> buscarGenero(String nombreGenero) {
		return gR.buscarGenero(nombreGenero);
	}

	@Override
	public List<Genero> findByNombreGeneroLikeIgnoreCase(String nombreGenero) {
		return gR.findByNombreGeneroLikeIgnoreCase(nombreGenero);
	}

}
