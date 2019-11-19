package pe.edu.upc.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.ImportDetails;
import pe.edu.upc.entity.Importation;
import pe.edu.upc.entity.LegalFiles;
import pe.edu.upc.service.IImportDetailService;
import pe.edu.upc.service.IImportationService;
import pe.edu.upc.service.ILegalFileService;
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

	@Autowired
	private ILegalFileService lS;

	@Autowired
	private IImportDetailService ideS;

	@RequestMapping("/new")
	public String irRegistrar(Model model) {
		model.addAttribute("listaTransportes", tS.list());
		model.addAttribute("importation", new Importation());
		return "importation/importation"; // vista
	}

	@RequestMapping("/newfile/{id}")
	public String irNewFile(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Date createdate = new Date();
		LegalFiles legalfile = new LegalFiles();
		legalfile.setCreateDate(createdate);
		model.put("legalfile", legalfile);
		model.put("listFiles", teS.list());

		Importation objimp = iS.listarId(id);
		model.put("importation", objimp);

		return "importation/files/legalFilesForm";
	}

	@RequestMapping("/{id}/updatefile/{idfile}")
	public String updateFile(@PathVariable(value = "id") Long id, @PathVariable(value = "idfile") Long idfile,
			Map<String, Object> model) {

		LegalFiles legalfile = lS.listarId(idfile);
		model.put("legalfile", legalfile);
		model.put("listFiles", teS.list());

		Importation objimp = iS.listarId(id);
		model.put("importation", objimp);

		return "importation/files/legalFilesForm";
	}

	@RequestMapping("/newproduct/{id}")
	public String irNewProduct(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		model.put("detail", new ImportDetails());
		model.put("listProducts", pS.listar());

		Importation objimp = iS.listarId(id);
		model.put("importation", objimp);

		return "importation/details/detailForm";
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
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Long id, RedirectAttributes flash) {
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

	@RequestMapping("{idimp}/eliminardetail/{id}")
	public String eliminarDetalle(Map<String, Object> model, @PathVariable(value = "id") Long idet,
			@PathVariable(value = "idimp") Long idimp, RedirectAttributes flash) {
		try {
			if (idet != null && idet > 0) {
				ideS.delete(idet);
				flash.addAttribute("mensaje", "Se elimino correctamente");
				flash.addAttribute("mensaje1", "Se elimino correctamente el id" + idet);
			} else
				return "redirect:/home";
		} catch (Exception e) {
			model.put("mensaje", "No se puede eliminar");
			model.put("error", e.getMessage());
		}
		String cadena = "redirect:/importation/detail/" + idimp;
		return cadena;
	}

	@GetMapping("/detail/{id}")
	public String detailImportation(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Importation imp = iS.listarId(id);

		if (imp == null) {
			flash.addFlashAttribute("error", "El Detalle no existe en la base de datos");
			return "importation/listImportation"; // vistaaaaa
		}
		model.put("importation", imp);
		model.put("titulo", "Detalle de Importacion #" + imp.getIdImportation());

		return "importation/details/listDetail"; // vista
	}

	@GetMapping(value = "/files/{id}")
	public String verDetalle(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		Importation imp = iS.listarId(id);

		if (imp == null) {
			flash.addFlashAttribute("error", "El Archivo no existe en la base de datos");
			return "importation/listImportation"; // vistaaaaa
		}
		model.put("importation", imp);
		model.put("titulo", "Archivos legales de Importacion #" + imp.getIdImportation());

		return "importation/files/listFiles"; // vista
	}

	@GetMapping(value = "/{idimp}/fileredaction/{id}")
	public String verRedacciondeArchivo(@PathVariable(value = "id") long id, @PathVariable(value = "idimp") long idimp,
			Map<String, Object> model, RedirectAttributes flash) {

		LegalFiles file = lS.listarId(id);
		Importation imp = iS.listarId(idimp);
		if (file == null) {
			flash.addFlashAttribute("error", "El Archivo no existe en la base de datos");
			return "redirect:/home"; // vistaaaaa
		}
		model.put("titulo", "Archivo redaccion de " + file.getTemplate().getTemplateName() + " para la importacion #"
				+ imp.getIdImportation());
		model.put("file", file);
		model.put("importation", imp);
		return "importation/files/viewFile"; // vista
	}

	@PostMapping("/save")
	public String saveOrder(@Valid Importation importation, Model model, SessionStatus status, BindingResult binRes) {
		Date requestday = new Date();
		try {
			importation.setRequestDate(requestday);
			iS.insert(importation);
			status.setComplete();
			model.addAttribute("success", "Orden Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect: /importation/new";
		}

		return "redirect:/importation/list";
	}

	@PostMapping("/saveproduct{id}")
	public String newProductXImportation(@PathVariable(value = "id") long id, @Valid ImportDetails importationdet,RedirectAttributes flash,
			BindingResult result, Model model, SessionStatus status) {
		Importation imp = iS.listarId(id);
		if(result.hasErrors())
		{
			flash.addFlashAttribute("error","El valor debe ser positivo");
			String cadena1 = "redirect:/importation/newproduct/" + id;
			return cadena1;
		}
		try {
			imp.addDetailImportation(importationdet);
			iS.insert(imp);
			status.isComplete();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
		}
		String cadena = "redirect:/importation/detail/" + id;
		return cadena;
	}

	// faltaaaa
	@PostMapping("/savefile{id}")
	public String save(@PathVariable(value = "id") long id, @Valid LegalFiles file, Model model, SessionStatus status) {
		Importation imp = iS.listarId(id);
		try {
			imp.addLegalFile(file);
			iS.insert(imp);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/home";
		}
		String cadena = "redirect:/importation/files/" + id;
		return cadena;
	}

}
