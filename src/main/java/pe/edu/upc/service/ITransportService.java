package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Transport;

public interface ITransportService {
	public Integer insert(Transport transport);

	public void delete(Long idTransport);

	Optional<Transport> listId(Long idTransport);

	List<Transport> list();

	List<Transport> findName(String transportName);
}
