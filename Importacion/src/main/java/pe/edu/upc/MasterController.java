package pe.edu.upc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hola")
@Controller
public class MasterController {

	@RequestMapping("/ex")
	public String Inicio()
	{
		return "ex";
	}
	
	@RequestMapping("/home")
	public String Home()
	{
		return "inicio";
	}
	
}
