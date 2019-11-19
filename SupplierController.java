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

import pe.edu.upc.entity.Supplier;
import pe.edu.upc.service.ISupplierService;



@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private ISupplierService sService;

	@RequestMapping("/welcome")
	public String irWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier"; // 1vista
	}

	@PostMapping("/save")
	public String saveSupplier(@Valid Supplier supplier, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supplier/supplier";
		} else {
			int rpta = sService.insert(supplier);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "supplier/supplier";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("supplier", new Supplier());
		return "redirect:/supplier/list"; // retorna controller
	}

	@GetMapping("/list")
	public String listProveedor(Model model) {
		try {
			model.addAttribute("supplier", new Supplier());
			model.addAttribute("listSupplier", sService.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "supplier/listSupplier"; // 2vista
	}

	@RequestMapping("/update/{id}")
	public String updateSupplier(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Supplier> supplier = sService.listId(id);

		if (supplier == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "supplier/listSupplier";
		} else {
			model.addAttribute("listSupplier", sService.list());

			model.addAttribute("supplier", supplier);
			return "supplier/supplier";
		}
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Supplier supplier) throws ParseException {

		List<Supplier> listaProveedores;
		listaProveedores = sService.findName(supplier.getSupplierName());

		if (listaProveedores.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaProveedores", listaProveedores);
		return "supplier/findSupplier"; // 3vista

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("supplier", new Supplier());
		return "supplier/findSupplier";
	}

	@RequestMapping("/eliminar")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				sService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listsuppliers", sService.list());

		return "redirect:/supplier/list";
	}
	
	@RequestMapping("/reporte3")
	public String supplierXimp(Map<String, Object> model) {
		model.put("listSupplierxImp", sService.supplierXimp());
		return "reports/supplierfrecuently";
	}

}
