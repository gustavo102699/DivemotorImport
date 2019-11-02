package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;

	
	@Pattern(regexp = "[A-Za-z]+", message = "El nombre del Producto no puede contener un número")
	@Column(name = "ProductName", nullable = false)
	private String productName;
	
	@DecimalMin("1.00")
	@Positive
	@Column(name = "Price", columnDefinition = "Decimal(8,2)", nullable = false)
	private Double price;

	@Positive
	private String unit;

	@ManyToOne
	@JoinColumn(name = "idBrand", nullable = false)
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "idCategory", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "idSupplier", nullable = false)
	private Supplier supplier;

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}



	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
