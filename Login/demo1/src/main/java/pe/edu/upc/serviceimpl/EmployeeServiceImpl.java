package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Employee;
import pe.edu.upc.repository.IEmployeeRepository;
import pe.edu.upc.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository eR;

	@Override
	public Integer insert(Employee employee) {
		int rpta = eR.findByEmployeeDni(employee.getDni());
		if (rpta == 0) {
			eR.save(employee);
		}
		return rpta;
	}

	@Override
	public List<Employee> list() {
		return eR.findAll();
	}

	@Override
	public Optional<Employee> listId(int idEmployee) {
		return eR.findById(idEmployee);
	}

	@Override
	public void delete(int idEmployee) {
		eR.deleteById(idEmployee);
	}

	@Override
	public List<Employee> findByEmployeeFirstName(String firstName) {
		return eR.findByFirstName(firstName);
	}

	@Override
	public List<Employee> findByEmployeeLastName(String lastName) {
		return eR.findByLastName(lastName);
	}

	@Override
	public List<Employee> findByEmployeeDni(int dni) {
		return eR.findByDni(dni);
	}

	@Override
	public List<Employee> findByFirstNameLikeIgnoreCase(String firstName) {
		return eR.findByFirstNameLikeIgnoreCase(firstName);
	}

	@Override
	public List<Employee> findByLastNameLikeIgnoreCase(String lastName) {
		return eR.findByLastNameLikeIgnoreCase(lastName);
	}

}
