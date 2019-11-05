package pe.edu.upc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Importation")
public class Importation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImportation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = true)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTransport", nullable = false)
	private Transport transport;

	@NotNull(message = "La fecha es obligatoria")
	@Column(name = "requestDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;// cuando se pidio

	@NotNull(message = "La fecha es obligatoria")
	@Future(message = "La fecha debe estar en el futuro")
	@Column(name = "deliveryDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;// cuando se quiere que se entrege

	@Column(name = "shippedDate", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedDate;// dia de entrega

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idImportation",nullable=true)
	private List<ImportDetails> import_details;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idImportation",nullable=true)
	private List<LegalFiles> legal_files;

	@PrePersist
	public void prePersist()
	{
		this.requestDate= new Date();
	}
	
	public Importation()
	{
		this.import_details= new ArrayList<ImportDetails>();
		this.legal_files= new ArrayList<LegalFiles>();
	}
	
	
	public Long getIdImportation() {
		return idImportation;
	}

	public void setIdImportation(Long idImportation) {
		this.idImportation = idImportation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public List<ImportDetails> getImport_details() {
		return import_details;
	}

	public void setImport_details(List<ImportDetails> import_details) {
		this.import_details = import_details;
	}

}
