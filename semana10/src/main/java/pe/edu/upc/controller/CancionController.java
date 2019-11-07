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

import pe.edu.upc.entity.Cancion;
import pe.edu.upc.service.ICancionService;
import pe.edu.upc.service.IGeneroService;

@Controller
@RequestMapping("/canciones")
public class CancionController {
	@Autowired
	private ICancionService cService;

	@Autowired
	private IGeneroService gService;

	@RequestMapping("/index")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newCancion(Model model) {
		model.addAttribute("cancion", new Cancion());
		model.addAttribute("listGeneros", gService.listar());
		return "/cancion/cancion";
	}

	@PostMapping("/save")
	public String saveCancion(@Valid Cancion cancion, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listGeneros", gService.listar());
			return "/cancion/cancion";
		} else {
			int rpta = cService.insertar(cancion);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/cancion/cancion";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();

			}
		}
		model.addAttribute("listCanciones", cService.listar());
		return "/cancion/listCanciones";
	}

	@GetMapping("/list")
	public String listCanciones(Model model) {
		try {
			model.addAttribute("cancion", new Cancion());
			model.addAttribute("listCanciones", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cancion/listCanciones";
	}

	@RequestMapping("/update/{id}")
	public String updateCancion(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Optional<Cancion> cancion = cService.listarId(id);
		if (cancion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/cancion/listCanciones";
		} else {
			model.addAttribute("listGeneros", gService.listar());
			model.addAttribute("cancion", cancion);
			return "/cancion/cancion";
		}
	}

	@RequestMapping("/delete")
	public String deleteCancion(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes flash) {
		try {
			// Optional<Cancion> cancion = cService.listarId(id);
			if (id != null && id > 0) {
				cService.eliminar(id);
				model.put("listCanciones", cService.listar());
			}
		} catch (Exception e) {
			model.put("mensaje", "Se elimino");
			model.put("listaMascotas", cService.listar());

		}
		return "/cancion/listCanciones";
	}

	@RequestMapping("/search")
	public String searchCancion(Map<String, Object> model, @ModelAttribute Cancion cancion) throws ParseException {

		List<Cancion> listCanciones;

		cancion.setNombreCancion(cancion.getNombreCancion());
		listCanciones = cService.buscarCancion(cancion.getNombreCancion());

		if (listCanciones.isEmpty()) {
			listCanciones = cService.findByNombreCancionLikeIgnoreCase(cancion.getNombreCancion());
		}

		if (listCanciones.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listCanciones", listCanciones);
		return "/cancion/listCanciones";
	}
}
