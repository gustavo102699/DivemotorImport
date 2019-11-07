package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Supplier")
public class Supplier implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSupplier;
	
	@Pattern(regexp = "[A-Za-z]+", message = "El nombre el Proveedor no puede contener un número")
	@Column(name = "SupplierName", nullable = false, length = 20)
	private String supplierName;

	public long getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
