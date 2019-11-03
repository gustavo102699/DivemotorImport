package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.spring.entity.Category;
import pe.edu.upc.spring.service.ICategoryService;



@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService cService;

	@RequestMapping("/welcome")
	public String irWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newCategoria(Model model) {
		model.addAttribute("category", new Category());
		return "category/category"; // 1vista
	}

	@PostMapping("/save")
	public String saveCategoria(@Valid Category category, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "category/category";
		} else {
			int rpta = cService.insert(category);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "category/category";
			} else {
				model.addAttribute("mensajeff", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("category", new Category());
		return "redirect:/category/list";
	}

	@GetMapping("/list")
	public String listCategoria(Model model) {
		try {
			model.addAttribute("category", new Category());
			model.addAttribute("listCategory", cService.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "category/listCategory"; // 2vista
	}

	@RequestMapping("/update/{id}")
	public String updateCategoria(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Category> category = cService.listId(id);

		if (category == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "category/listCategory";
		} else {
			model.addAttribute("listCategory", cService.list());

			model.addAttribute("category", category);
			return "category/category";
		}
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Category category) throws ParseException {

		List<Category> listaCategorias;
		listaCategorias = cService.findName(category.getCategoryName());

		if (listaCategorias.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaCategories", listaCategorias);
		return "category/findCategory"; // 3vista

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("category", new Category());
		return "category/findCategory";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una libreria");
		}
		model.put("listcategories", cService.list());

		return "category/listCategory";
	}

}
