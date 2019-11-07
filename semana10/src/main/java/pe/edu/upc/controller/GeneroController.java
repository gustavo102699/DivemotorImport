package pe.edu.upc.controller;

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

import pe.edu.upc.entity.Genero;
import pe.edu.upc.service.IGeneroService;

@Controller
@RequestMapping("/generos")
public class GeneroController {
	@Autowired
	private IGeneroService gService;

	@RequestMapping("/index")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newGenero(Model model) {
		model.addAttribute("genero", new Genero());
		return "/genero/genero";
	}

	@PostMapping("/save")
	public String saveGenero(@Valid Genero genero, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/genero/genero";
		} else {
			int rpta = gService.insertar(genero);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/genero/genero";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
			model.addAttribute("listGeneros", gService.listar());
		}
		return "/genero/listGeneros";
	}

	@GetMapping("/list")
	public String listGeneros(Model model) {
		try {
			model.addAttribute("genero", new Genero());
			model.addAttribute("listGeneros", gService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/genero/listGeneros";
	}

	@RequestMapping("/update/{id}")
	public String updateGenero(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Genero> genero = gService.listarId(id);

		if (genero == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/genero/listGeneros";
		} else {
			// model.addAttribute("listGeneros", gService.listar());

			model.addAttribute("genero", genero);
			return "/genero/genero";
		}
	}

	@RequestMapping("/delete")
	public String deleteGenero(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {

				gService.eliminar(id);
				model.put("listGeneros", gService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una  asignada");
			model.put("listGeneros", gService.listar());

		}
		return "/genero/listGeneros";
	}

	@RequestMapping("/search")
	public String searchGenero(Map<String, Object> model, @ModelAttribute Genero genero) throws ParseException {

		List<Genero> listGeneros;

		genero.setNombreGenero(genero.getNombreGenero());
		listGeneros = gService.buscarGenero(genero.getNombreGenero());

		if (listGeneros.isEmpty()) {
			listGeneros = gService.findByNombreGeneroLikeIgnoreCase(genero.getNombreGenero());
		}

		if (listGeneros.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listGeneros", listGeneros);
		return "/genero/listGeneros";
	}
}
