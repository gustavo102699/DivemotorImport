package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Importation;
import pe.edu.upc.service.IImportationService;
import pe.edu.upc.service.ITransportService;

@Controller
@RequestMapping("/importation")
public class ImportationController {
	
	@Autowired
	private ITransportService tS;
	
	@Autowired
	private IImportationService iS;
	
	@RequestMapping("/new")
	public String irRegistrar(Model model) {
		model.addAttribute("listaTransportes", tS.list());
		model.addAttribute("importation", new Importation());
		return "importation/importation"; // vista
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Importation objImp = iS.listarId(id);
		if (objImp == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/products/listar";
		} else {
			model.addAttribute("listaTransportes", tS.list());
			model.addAttribute("importation", objImp);
			return "importation/importation";
		}
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {

		model.put("listaImportations", iS.listar());
		return "importation/listImportation";
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes flash) {
		try {
			Importation imp = iS.listarId(id);
			if (id != null && id > 0) {
				iS.eliminar(id);
				model.put("listaImportations", iS.listar());
			}
		} catch (Exception e) {
			model.put("mensaje", "Se eliminÃ³");
			model.put("listaImportations", iS.listar());

		}
		return "importation/listImportation"; // vistas
	}
	
	
	@GetMapping("/detail/{id}")
	public String detailImportation(@PathVariable(value = "id") Long id, Model model) {

		try {
			Optional<Importation> imp = iS.fetchByImportIdWhithImportDetailsWithProduct(id);

			if (!imp.isPresent()) {
				model.addAttribute("error", "Orden no existe");
				return "redirect:/customers/list";
			}

			model.addAttribute("importation", imp.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "importation/importationDetails";
	}
	
	@GetMapping("/files/{id}")
	public String legalFiles(@PathVariable(value = "id") Long id, Model model) {

		try {
			Optional<Importation> imp = iS.fetchByImportIdWhithLegalFilesWithFileTemplate(id);

			if (!imp.isPresent()) {
				model.addAttribute("error", "Orden no existe");
				return "redirect:/importation/list";
			}

			model.addAttribute("importation", imp.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "importation/legalFiles";
	}
	
	
}