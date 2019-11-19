package pe.edu.upc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MasterController {

	@RequestMapping("/home")
	public String Inicio()
	{
		return "home";
	}
	
	@RequestMapping("/reportes")
	public String Report()
	{
		return "reports/reports";
	}
	
	@RequestMapping("/")
	public String Home()
	{
		return "redirect:/bienvenidos/";
	}
	
}
