package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

import pe.edu.upc.entity.Role;
import pe.edu.upc.service.IRoleService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private IRoleService rService;

	@Autowired
	private IUserService uService;

	@Secured("ROLE_USER")
	@RequestMapping("/index")
	public String welcome() {
		return "welcome";
	}

	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newPosition(Model model) {
		model.addAttribute("role", new Role());
		return "/role/role";
	}

	@PostMapping("/save")
	public String saveRole(@Valid Role role, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/role/role";
		} else {
			uService.insertRole(role.getRoleName(), role.getIdUser());
		}
		return "/role/listRoles";
	}

	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listRoles(Model model) {
		try {
			model.addAttribute("role", new Role());
			model.addAttribute("listRoles", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/role/listRoles";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/update/{id}")
	public String updateRole(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Role> role = rService.listId(id);

		if (role == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/roles/list";
		} else {
			// model.addAttribute("listGeneros", gService.listar());

			model.addAttribute("role", role);
			return "/role/role";
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping("/delete")
	public String deleteRole(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {

				rService.delete(id);
				model.put("listRoles", rService.list());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una  asignada");
			model.put("listRoles", rService.list());

		}
		return "/role/listRoles";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/find")
	public String findRole(Map<String, Object> model, @ModelAttribute Role role) throws ParseException {

		List<Role> listRoles;

		role.setRoleName(role.getRoleName());
		listRoles = rService.findByRoleName(role.getRoleName());

		if (listRoles.isEmpty()) {
			listRoles = rService.findByRoleNameLikeIgnoreCase(role.getRoleName());
		}

		if (listRoles.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listRoles", listRoles);
		return "/role/listRoles";
	}
}
