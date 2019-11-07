package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Transport;

public interface ITransportService {
	public Integer insert(Transport transport);

	public void delete(long idTransport);

	Optional<Transport> listId(long idTransport);

	List<Transport> list();

	List<Transport> findName(String transportName);
}
