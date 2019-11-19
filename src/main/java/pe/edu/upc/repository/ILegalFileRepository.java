package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.LegalFiles;

@Repository
public interface ILegalFileRepository extends JpaRepository<LegalFiles, Long> {
	
	/*/query para validar template repetido*
	@Query("select count(l.brandName) from Brand l where l.brandName=:brandName")
	public int buscarFileinImp(@Param("templateName") String nombreTemplate);*/
}
