package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.FileTemplate;
import pe.edu.upc.service.ITemplateService;

@Controller
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private ITemplateService tS;
	private int valid = 0;

	@GetMapping("/list")
	public String listTemplates(Model model) {
		try {
			ingresar_datos();
			model.addAttribute("template", new FileTemplate());
			model.addAttribute("listTemplate", tS.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "template/listTemplate";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute FileTemplate temp) throws ParseException {

		List<FileTemplate> listaTemplates;
		listaTemplates = tS.findName(temp.getTemplateName());

		if (listaTemplates.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listTemplate", listaTemplates);
		return "template/findTemplate";

	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		FileTemplate temp = tS.listId(id);
		if (temp == null) {
			flash.addFlashAttribute("error", "El Archivo no existe en la base de datos");
			return "template/listTemplate"; // vistaaaaa
		}
		model.put("template", temp);
		model.put("titulo", "Requisitos para la redaccion de : " + temp.getTemplateName());

		return "template/templatebody"; // vista
	}

	private void ingresar_datos() {

		if (valid == 0) {

			FileTemplate temp = new FileTemplate(null, "Contrato de remuneracion",
					"Éste scerla, decide no hacerlo, faltando a la buena fe contractual. Veamos un ejemplo: si el que hizo la oferta se niega a abrir la carta que presumiblemente contendrá la aceptación, se entiende que hay consentimiento mutuo para celeb");
			FileTemplate temp2 = new FileTemplate(null, "Certificado de salubridad", "Los requisitos son estos");
			FileTemplate temp3 = new FileTemplate(null, "Certificado de sanidad", "ej 2");
			tS.insert(temp);
			tS.insert(temp2);
			tS.insert(temp3);
			valid++;
		}

	}
}
