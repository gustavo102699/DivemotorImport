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
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private IUserService uService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/index")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "/employee/employee";
	}

	@PostMapping("/save")
	public String saveEmployee(@Valid Employee employee, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/employee/employee";
		} else {
			Users aux = new Users();
			aux.setUsername(employee.getUser().getUsername());
			aux.setPassword(employee.getUser().getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			uService.insert(aux);
			aux = uService.findByUserName(employee.getUser().getUsername());

			uService.insertRole("ROLE_USER", aux.getIdUser());

			employee.setUser(aux);

			int rpta = eService.insert(employee);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/employee/employee";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
			model.addAttribute("listEmployees", eService.list());
		}
		return "/employee/listEmployees";
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listEmployees(Model model) {
		try {
			model.addAttribute("employee", new Employee());
			model.addAttribute("listEmployees", eService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/employee/listEmployees";
	}

	@RequestMapping("/update/{id}")
	public String updateemployee(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Employee> employee = eService.listId(id);

		if (employee == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/employees/list";
		} else {
			model.addAttribute("employee", employee);
			return "/employee/employee";
		}
	}
	
	
	@RequestMapping("/delete")
	public String deleteEmployee(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {

				eService.delete(id);
				model.put("listEmployees", eService.list());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una  asignada");
			model.put("listEmployees", eService.list());

		}
		return "/employee/listemployees";
	}
	
	@RequestMapping("/find")
	public String findEmployee(Map<String, Object> model, @ModelAttribute Employee employee) throws ParseException {

		List<Employee> listEmployees;

		employee.setLastName(employee.getLastName());
		listEmployees = eService.findByEmployeeLastName(employee.getLastName());

		if (listEmployees.isEmpty()) {
			listEmployees = eService.findByLastNameLikeIgnoreCase(employee.getLastName());
		}

		if (listEmployees.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listEmployees", listEmployees);
		return "/employee/listEmployees";
	}

	@GetMapping("/detail/{id}")
	public String detailsEmployee(@PathVariable(value = "id") Integer id, Model model) {
		try {
			Optional<Employee> employee = eService.listId(id);

			if (!employee.isPresent()) {
				model.addAttribute("mensaje", "No existe");
				return "redirect:/employees/list";
			} else {
				model.addAttribute("employee", employee.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/employee/detail";
	}
}
