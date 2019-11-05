package pe.edu.upc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ImportDetails")
public class ImportDetails {

	@Id
	private Long idImportDetails;
	
	@ManyToOne
	@JoinColumn(name = "idProduct", nullable = false)
	private Product product;
	
	private int quantity;

	public Double calcularSubTotal()
	{
		return quantity*product.getPrice();
	}
	
	public Long getIdImportDetails() {
		return idImportDetails;
	}

	public void setIdImportDetails(Long idImportDetails) {
		this.idImportDetails = idImportDetails;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
