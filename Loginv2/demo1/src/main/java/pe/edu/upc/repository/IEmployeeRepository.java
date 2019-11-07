package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	// userFirstName
	@Query("select count(e.firstName) from Employee e where e.firstName =:firstName")
	public int findByEmployeeFirstName(String firstName);

	@Query("select e from Employee e where e.firstName like %:firstName%")
	List<Employee> findByFirstName(String firstName);

	List<Employee> findByFirstNameLikeIgnoreCase(String firstName);

	// userLastName
	@Query("select count(e.lastName) from Employee e where e.lastName =:lastName")
	public int findByEmployeeLastName(String lastName);

	@Query("select e from Employee e where e.lastName like %:lastName%")
	List<Employee> findByLastName(String lastName);

	List<Employee> findByLastNameLikeIgnoreCase(String lastName);

	// dni
	@Query("select count(e.dni) from Employee e where e.dni =:dni")
	public int findByEmployeeDni(String dni);

	@Query("select e from Employee e where e.dni=:dni")
	List<Employee> findByDni(String dni);
}
