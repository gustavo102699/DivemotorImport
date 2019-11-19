package pe.edu.upc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Employee;
import pe.edu.upc.entity.Position;
import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IEmployeeService;
import pe.edu.upc.service.IPositionService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping
public class LoginController {

	private int valid = 0;
	private int valid2 = 0;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUserService uS;

	@Autowired
	private IPositionService pS;

	@Autowired
	private IEmployeeService eS;// change

	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		insertEmployeePred();
		insertEmployeePred2();
		if (principal != null) {
			return "redirect:/home";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}

		return "login";
	}

	private void insertEmployeePred() {

		if (valid == 0) {

			Employee emp = new Employee();

			Position pos = new Position();
			pos.setPositionName("Administrador");

			pS.insert(pos);
			emp.setPosition(pos);

			Users aux = new Users();
			aux.setUsername("admin");
			aux.setPassword("divemotor");
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			uS.insert(aux);

			aux = uS.findUsername("admin");

			uS.insertRole("ROLE_ADMIN", aux.getIdUser());

			emp.setUser(aux);

			emp.setFirstName("Anthony");
			emp.setLastName("Shuan");
			emp.setDni("50505050");
			emp.setEmail("admin@divemotor.com");

			int rpta = eS.insert(emp);

			valid++;
		}
	}

	private void insertEmployeePred2() {

		if (valid2 == 0) {

			Employee emp = new Employee();

			Position pos = new Position();
			pos.setPositionName("Jefe");

			pS.insert(pos);
			emp.setPosition(pos);

			Users aux = new Users();
			aux.setUsername("jefe");
			aux.setPassword("divemotor");
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			aux.setPassword(bcryptPassword);
			uS.insert(aux);

			aux = uS.findUsername("jefe");

			uS.insertRole("ROLE_JEFE", aux.getIdUser());

			emp.setUser(aux);

			emp.setFirstName("Andres");
			emp.setLastName("Mendez");
			emp.setDni("40404400");
			emp.setEmail("jefe@divemotor.com");

			int rpta2 = eS.insert(emp);

			valid2++;
		}
	}

}
