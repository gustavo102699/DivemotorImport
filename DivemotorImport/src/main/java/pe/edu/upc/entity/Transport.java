package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Transport")
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransport;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del transporte no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del transporte no puede contener un número")
	@Column(name = "transportName", nullable = false, length = 30)
	private String transportName;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la agencia no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la agencia no puede contener un número")
	@Column(name = "importAgency", nullable = false, length = 30)
	private String importAgency;
	

	public Long getIdTransport() {
		return idTransport;
	}

	public void setIdTransport(Long idTransport) {
		this.idTransport = idTransport;
	}

	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

	public String getImportAgency() {
		return importAgency;
	}

	public void setImportAgency(String importAgency) {
		this.importAgency = importAgency;
	}


	
	
}
