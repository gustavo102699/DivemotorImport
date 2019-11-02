package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Transport")
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransport;
	
	@Column(name = "transportName", nullable = false, length = 30)
	private String transportName;
	
	@Column(name = "importAgency", nullable = false, length = 30)
	private String importAgency;
	
	private Boolean disponibility;

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

	public Boolean getDisponibility() {
		return disponibility;
	}

	public void setDisponibility(Boolean disponibility) {
		this.disponibility = disponibility;
	}
	
	
}
