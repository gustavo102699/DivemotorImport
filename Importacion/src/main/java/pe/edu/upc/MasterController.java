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
	
	@RequestMapping("/")
	public String Home()
	{
		return "redirect:/bienvenidos/";
	}
	
}
