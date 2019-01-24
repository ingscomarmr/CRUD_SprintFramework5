package com.comr.crud.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.comr.crud.model.entity.Grado;
import com.comr.crud.service.GradoServiceImp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GradoController {

	private static final Log log = LogFactory.getLog(GradoController.class);
	
	@Autowired	
	GradoServiceImp gradoService;
	
	@GetMapping("/grados")
	public String getGradoView(Model model, @RequestParam(defaultValue="",name="txtBuscar",required=false) String buscar) {		
		log.info("/grados -> ver todos los grados filtrados por:" + buscar);
		List<Grado> gradoList = gradoService.getGradosToGrid(buscar);				
		model.addAttribute("buscar",buscar);
		model.addAttribute("gradosList", gradoList);
		
		return "grados";//name of view
	}
	
	@RequestMapping(value="/grado_ver", method=RequestMethod.GET)
	public String getGradoById(Model model,@RequestParam(defaultValue="0", name="gradoId") Integer id) {
		log.info("/grado_ver -> ver el grado " + id);
		Grado grado = new Grado();
		if(id > 0) {
			grado = gradoService.findById(id);
		}
		model.addAttribute("grado", grado);
		return "grado_ver";
	}
	
	@RequestMapping(value="/grado_guardar", method=RequestMethod.POST)
	public String saveGrado(Grado g) {
		log.info("/grado_guardar -> guardar el grado");
		try {
			if(g != null) {
				gradoService.Guardar(g);
			}
		}catch(Exception ex) {
			log.error("Error al guardar", ex);			
		}			
		return "redirect:/grados";
	}
	
	@RequestMapping(value="/grado_eliminar/{id}", method=RequestMethod.GET)
	public String eliminarGrado(@PathVariable(value="id") int id) {
		log.info("/grado_ver -> eliminar el grado:" + id);
		gradoService.Eliminar(id);		
		return "redirect:/grados";
	}
	
}
