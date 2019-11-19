package pe.edu.upc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bienvenidos")
public class LoginController {
	@RequestMapping("/")
	public String irWelcome() {
		return "ex";
	}
}
