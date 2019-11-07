package pe.edu.upc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Importation;

public interface IImportationRepository extends JpaRepository<Importation, Long>{
	
	@Query("select i from Importation i join fetch i.import_details ide join fetch ide.product where i.idImportation=?1")
	Optional<Importation> fetchByImportIdWhithImportDetailsWithProduct(Long id);
	
	@Query("select i from Importation i join fetch i.legal_files lf join fetch lf.template where i.idImportation=?1")
	Optional<Importation> fetchByImportIdWhithLegalFilesWithFileTemplate(Long id);

}
