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
@Table(name = "Brand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idBrand;

	@Pattern(regexp = "[A-Za-z]+", message = "El nombre de la Marca no puede contener un número")
	@Column(name = "BrandName", nullable = false, length = 20)
	private String brandName;

	@Pattern(regexp = "[A-Za-z]+", message = "El nombre de la Empresa no puede contener un número")
	@Column(name = "Enterprise", nullable = false, length = 20)
	private String enterprise;

	public long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(long idBrand) {
		this.idBrand = idBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

}
