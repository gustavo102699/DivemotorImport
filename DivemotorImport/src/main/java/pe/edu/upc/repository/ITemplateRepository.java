package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.FileTemplate;

@Repository
public interface ITemplateRepository extends JpaRepository<FileTemplate, Long>{

	@Query("from Transport p where p.transportName like %:transportName%")
	List<FileTemplate> buscarNombre(@Param("transportName") String nombreTransport);
}
