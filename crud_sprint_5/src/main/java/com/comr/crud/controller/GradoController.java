package com.comr.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.comr.crud.model.entity.Grado;
import com.comr.crud.repository.GradoCrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GradoController {

	@Autowired
	@Qualifier("gradoCrudRepository")
	GradoCrudRepository gradoCrudRepository;
	
	@GetMapping("/grados")
	public String getGradoView(Model model, @RequestParam(defaultValue="",name="txtBuscar",required=false) String buscar) {		

		List<Grado> gradoList = null;
		if(buscar != null && buscar.length() > 0) {			
			gradoList = gradoCrudRepository.findByNombreLikeAndEliminadoOrderByNombre("%" + buscar + "%", 0);
		}else {
			gradoList = gradoCrudRepository.findByEliminadoOrderByNombreDesc(0);
		}
		
		model.addAttribute("buscar",buscar);
		model.addAttribute("gradosList", gradoList);
		
		return "grados";//name of view
	}
	
	@RequestMapping(value="/editar_grado", method=RequestMethod.GET)
	public String requestMethodName(@RequestParam String param) {
		return "editar_grado";
	}
	
	
}
