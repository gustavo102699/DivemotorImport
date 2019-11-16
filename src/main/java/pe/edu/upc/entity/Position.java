package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Position")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPosition;
	
	@Pattern(regexp = "[A-Za-z]+", message = "El nombre de la posicion no puede contener un número")
	@Column(name = "namePosition", nullable = false, length = 20)
	private String namePosition;

	public Long getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(Long idPosition) {
		this.idPosition = idPosition;
	}

	public String getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(String namePosition) {
		this.namePosition = namePosition;
	}

	public Position(Long idPosition,
			@Pattern(regexp = "[A-Za-z]+", message = "El nombre de la posicion no puede contener un número") String namePosition) {
		super();
		this.idPosition = idPosition;
		this.namePosition = namePosition;
	}

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
