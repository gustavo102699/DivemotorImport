package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Por favor, complete el nombre del usuario")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@NotEmpty(message = "Por favor, complete el apellido del usuario")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Size(min = 8, max = 8, message = "El DNI debe ser de 8 dígitos")
	@NotEmpty(message = "Debe ingresar nombre de genero")
	@Column(name = "dni", nullable = false, length = 50)
	private int dni;

	@Email(message = "El correo electronico ingresado no es valido")
	@NotEmpty(message = "Por favor, complete el correo electrónico del usuario")
	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@OneToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id")
	private Users user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
