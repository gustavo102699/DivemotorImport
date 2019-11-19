package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Transport;
import pe.edu.upc.repository.ITransportRepository;
import pe.edu.upc.service.ITransportService;

@Service
public class TransportServiceImpl implements ITransportService {

	@Autowired
	private ITransportRepository tR;

	@Override
	public Integer insert(Transport transport) {
		int rpta = tR.buscarTransport(transport.getTransportName());
		if (rpta == 0) {
			tR.save(transport);
		}
		return rpta;
	}

	@Override
	public void delete(Long idTransport) {
		tR.deleteById(idTransport);
	}

	@Override
	public Optional<Transport> listId(Long idTransport) {
		return tR.findById(idTransport);
	}

	@Override
	public List<Transport> list() {
		return tR.findAll(Sort.by(Sort.Direction.ASC, "transportName"));
	}

	@Override
	public List<Transport> findName(String transportName) {
		return tR.buscarNombre(transportName);
	}

}
