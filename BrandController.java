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

import pe.edu.upc.entity.Brand;
import pe.edu.upc.service.IBrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private IBrandService bService;


	@GetMapping("/new")
	public String newLBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "brand/brand";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Brand brand, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "brand/brand";
		} else {
			int rpta = bService.insert(brand);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "brand/brand";
			} else {
				model.addAttribute("mensajeff", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("brand", new Brand());
		return "redirect:/brand/list";
	}

	@GetMapping("/list")
	public String listMarcas(Model model) {
		try {
			model.addAttribute("brand", new Brand());
			model.addAttribute("listBrand", bService.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "brand/listBrand";
	}

	@RequestMapping("/update/{id}")
	public String updateMarca(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Brand> brand = bService.listId(id);

		if (brand == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "brand/listBrand";
		} else {
			model.addAttribute("listBrand", bService.list());

			model.addAttribute("brand", brand);
			return "brand/brand";
		}
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Brand brand) throws ParseException {

		List<Brand> listaMarcas;
		listaMarcas = bService.findName(brand.getBrandName());

		if (listaMarcas.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaBrands", listaMarcas);
		return "brand/findBrand";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("brand", new Brand());
		return "brand/findBrand";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				bService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una libreria");
		}
		model.put("listbrands", bService.list());

		return "brand/listBrand";
	}
}
