package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Cancion;
import pe.edu.upc.repository.ICancionRepository;
import pe.edu.upc.service.ICancionService;

@Service
public class CancionServiceImpl implements ICancionService {

	@Autowired
	private ICancionRepository cR;

	@Override
	public Integer insertar(Cancion cancion) {
		int rpta = cR.findByNombreCancion(cancion.getNombreCancion());
		if (rpta == 0) {
			cR.save(cancion);
		}
		return rpta;
	}

	@Override
	public List<Cancion> listar() {
		return cR.findAll();
	}

	@Override
	public Optional<Cancion> listarId(int idCancion) {
		return cR.findById(idCancion);
	}

	@Override
	public void eliminar(int idCancion) {
		cR.deleteById(idCancion);

	}

	@Override
	public List<Cancion> buscarCancion(String nombreCancion) {
		return cR.buscarCancion(nombreCancion);
	}

	@Override
	public List<Cancion> findByNombreCancionLikeIgnoreCase(String nombreCancion) {
		return cR.findByNombreCancionLikeIgnoreCase(nombreCancion);
	}

}
