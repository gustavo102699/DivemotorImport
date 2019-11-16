package pe.edu.upc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MasterController {

	@RequestMapping("/ex")
	public String Inicio()
	{
		return "ex";
	}
	
	@RequestMapping("/")
	public String Home()
	{
		return "inicio";
	}
	
}
