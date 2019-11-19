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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Transport;
import pe.edu.upc.service.ITransportService;

@Controller
@RequestMapping("/transport")
public class TransportController {

	@Autowired
	private ITransportService tS;
	
	@GetMapping("/new")
	public String newTransport(Model model) {
		model.addAttribute("transport", new Transport());
		return "transport/transport";
	}

	@PostMapping("/save")
	public String saveTransport(@Valid Transport transport, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "transport/transport";
		} else {
			int rpta = tS.insert(transport);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "transport/transport";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("transport", new Transport());
		return "redirect:/transport/list";
	}

	@GetMapping("/list")
	public String listTransport(Model model) {
		try {
			model.addAttribute("transport", new Transport());
			model.addAttribute("listTransport", tS.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "transport/listTransport";
	}

	@RequestMapping("/update/{id}")
	public String updateTransport(@PathVariable Long id, Model model, RedirectAttributes objRedir) {
		Optional<Transport> transport = tS.listId(id);

		if (transport == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "transport/listTransport";
		} else {
			model.addAttribute("listTransport", tS.list());

			model.addAttribute("transport", transport);
			return "transport/transport";
		}
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Transport transport) throws ParseException {

		List<Transport> listaTransports;
		listaTransports = tS.findName(transport.getTransportName());

		if (listaTransports.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaBrands", listaTransports);
		return "transport/findTransport";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("transport", new Transport());
		return "transport/findTransport";
	}

	@RequestMapping("/eliminar/{id}")
	public String delete(Map<String, Object> model, @PathVariable(value = "id") Long id) {
		try {
			if (id != null && id > 0) {
				tS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una libreria");
		}
		model.put("listTransport", tS.list());

		return "transport/listTransport";
	}
}
