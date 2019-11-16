package pe.edu.upc.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.FileTemplate;
import pe.edu.upc.entity.ImportDetails;
import pe.edu.upc.entity.Importation;
import pe.edu.upc.service.IImportationService;
import pe.edu.upc.service.IProductService;
import pe.edu.upc.service.ITemplateService;
import pe.edu.upc.service.ITransportService;

@Controller
@RequestMapping("/importation")
public class ImportationController {
	
	@Autowired
	private ITransportService tS;
	
	@Autowired
	private IImportationService iS;
	
	@Autowired
	private IProductService pS;
	
	@Autowired
	private ITemplateService teS;
	
	@RequestMapping("/new")
	public String irRegistrar(Model model) {
		model.addAttribute("listaTransportes", tS.list());
		model.addAttribute("importation", new Importation());
		return "importation/importation"; // vista
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable Long id, Model model, RedirectAttributes objRedir) {

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
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Long id,
			RedirectAttributes flash) {
		try {
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
				return "redirect:/importation/list";
			}
			

			model.addAttribute("importation", imp.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "importation/importationDetails";
	}
	
	
	
	
	@GetMapping(value = "/files/{id}")
	public String ver(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		Importation imp = iS.listarId(id);
		ImportDetails idet = new ImportDetails();
		
		if (imp == null) {
			flash.addFlashAttribute("error", "El Archivo no existe en la base de datos");
			return "template/listTemplate"; // vistaaaaa
		}
		model.put("importation", imp);
		model.put("importationDetail", idet);
		model.put("listTemplates", teS.list());
		model.put("titulo", "Archivos legales de Importacion# : " + imp.getIdImportation());

		return "template/templatebody"; // vista
	}
	
	/*
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
	*/
	
	@PostMapping("/save")
	public String saveOrder(@Valid Importation importation, Model model, SessionStatus status) {
		Date requestday= new Date();
		try {
			importation.setRequestDate(requestday);
			iS.insert(importation);
			status.setComplete();
			model.addAttribute("success", "Orden Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "redirect:/importation/list";
	}
	
	
}
