package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	// userFirstName
	@Query("select count(e.first_name) from Employee e where e.first_name =:name")
	public int findByEmployeeFirstName(@Param("name") String firstName);

	@Query("select e from Employee e where e.first_name like %:name%")
	List<Employee> findByFirstName(@Param("name") String firstName);

	List<Employee> findByFirstNameLikeIgnoreCase(String firstName);

	// userLastName
	@Query("select count(e.last_name) from Employee e where e.last_name =:name")
	public int findByEmployeeLastName(@Param("name") String lastName);

	@Query("select e from Employee e where e.last_name like %:name%")
	List<Employee> findByLastName(@Param("name") String lastName);

	List<Employee> findByLastNameLikeIgnoreCase(String lastName);

	// dni
	@Query("select count(e.dni) from Employee e where e.dni =:dni")
	public int findByEmployeeDni(int dni);

	@Query("select e from Employee e where e.dni=:dni")
	List<Employee> findByDni(int dni);
}
