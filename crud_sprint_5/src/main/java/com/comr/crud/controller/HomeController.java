package com.comr.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// a todoso los controladores se les pone la anotacion de @Controller para que los identifique 
@Controller
public class HomeController {

	@GetMapping("/") //indicamos con que ruta va a contestar este metodo
	public String home(Model model) {	//si la agregamos el parametro model podremos enviar parametros a la view
		model.addAttribute("title", "HOME / DEFAULT"); //se agrega el parametro a la view
		return "home"; //este es el nombre de la view que se correra y siempre esta en resources/templates
	}
}
