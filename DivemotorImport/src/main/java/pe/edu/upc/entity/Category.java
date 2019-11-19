package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategory;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Categoría no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Categoría no puede contener un número")
	@Column(name = "CategoryName", nullable = false, length = 20)
	private String categoryName;

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
