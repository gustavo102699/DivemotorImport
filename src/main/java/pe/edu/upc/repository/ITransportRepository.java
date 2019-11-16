package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Transport;

@Repository
public interface ITransportRepository extends JpaRepository<Transport, Long>{
	@Query("select count(t.transportName) from Transport t where t.transportName=:transportName")
	public int buscarTransport(@Param("transportName") String nombre);

	@Query("from Transport p where p.transportName like %:transportName%")
	List<Transport> buscarNombre(@Param("transportName") String nombreTransport);

}
