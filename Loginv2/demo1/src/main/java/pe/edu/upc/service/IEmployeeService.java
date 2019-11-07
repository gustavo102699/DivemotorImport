package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Employee;

public interface IEmployeeService {
	public Integer insert(Employee employee);

	public List<Employee> list();

	Optional<Employee> listId(int idEmployee);

	public void delete(int idEmployee);
	
	List<Employee> findByEmployeeFirstName(String firstName);

	List<Employee> findByEmployeeLastName(String lastName);
	
	List<Employee> findByEmployeeDni(String dni);
	
	public List<Employee> findByFirstNameLikeIgnoreCase(String firstName);

	public List<Employee> findByLastNameLikeIgnoreCase(String lastName);
}
