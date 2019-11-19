package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Employee;
import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IEmployeeService;
import pe.edu.upc.service.IPositionService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService eS;// change
	
	//private int valid = 0;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUserService uS;
	
	@Autowired
	private IPositionService pS;

	@RequestMapping("/index")
	public String welcome() {
		return "welcome";
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("listPositions", pS.list());
		return "employee/employee";
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/save")
	public String saveEmployee(@Valid Employee employee, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listPositions", pS.list());
			return "employee/employee";
		} else {
			Users aux = new Users();
			aux.setUsername(employee.getUser().getUsername());
			aux.setPassword(employee.getUser().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			uS.insert(aux);

			aux = uS.findUsername(employee.getUser().getUsername());

			if(employee.getPosition().getIdPosition() == 1) {
				uS.insertRole("ROLE_ADMIN", aux.getIdUser());
			}
			
			if(employee.getPosition().getIdPosition() == 2) {
				uS.insertRole("ROLE_ENCA", aux.getIdUser());
			}
			
			if(employee.getPosition().getIdPosition() == 3) {
				uS.insertRole("ROLE_JEFE", aux.getIdUser());
			}
			

			employee.setUser(aux);

			int rpta = eS.insert(employee);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "employee/employee";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}

			model.addAttribute("listEmployees", eS.list());
			return "/employee/listEmployees";
		}
	}

	@Secured({"ROLE_ADMIN","ROLE_JEFE"})
	@GetMapping("/list")
	public String listEmployees(Model model) {
		try {
			//insertEmployeePred();
			model.addAttribute("employee", new Employee());
			model.addAttribute("listEmployees", eS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/employee/listEmployees";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				eS.delete(id);
				model.put("listEmployees", eS.list());
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No de puede eliminar");
		}

		return "/employee/listEmployees";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/update/{id}")
	public String updateEmployee(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Optional<Employee> employee = eS.findById(id);
		if (employee == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/employees/list";
		} else {
			model.addAttribute("listPositions", pS.list());
			model.addAttribute("employee", employee);
			return "/employee/employeeup";
		}
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/find")
	public String findEmployee(Map<String, Object> model, @ModelAttribute Employee employee) throws ParseException {

		List<Employee> listEmployees;

		employee.setLastName(employee.getLastName());
		listEmployees = eS.findEmployeeByLastName(employee.getLastName());

		if (listEmployees.isEmpty()) {
			listEmployees = eS.findEmployeeByLastNameLikeIgnoreCase(employee.getLastName());
		}

		if (listEmployees.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listEmployees", listEmployees);
		return "/employee/listEmployees";
	}
	
	
	

}
