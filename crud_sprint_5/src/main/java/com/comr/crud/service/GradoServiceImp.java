package com.comr.crud.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.comr.crud.model.entity.Grado;
import com.comr.crud.repository.GradoCrudRepository;

/* *
 * IMPLEMENTAMOS EL SERVICE PORQUE EN ESTE SE TENDRA TODA LA LOGICA
 * ES MEJOR USAR UN SERVICE QUE USAR MUCHOS DAO O REPOSITORY EN EL 
 * CONTROLLER, SE RECOMIENDA SU USO DE ESTA FORMA
 * */
@Service
public class GradoServiceImp implements IGradoService {

	private static final Log log = LogFactory.getLog(GradoServiceImp.class);
	
	@Autowired
	@Qualifier("gradoCrudRepository")
	GradoCrudRepository gradoCrudRepository;
	
	@Override
	public List<Grado> getGradosToGrid(String buscar) {
		log.info("Obtner grados de base de datos bucando: " + buscar);
		if(buscar != null && buscar.length() > 0) {
			log.info("Buscando los grado que tengan %" + buscar + "%");
			return gradoCrudRepository.findByNombreLikeIgnoreCaseAndEliminadoOrderByNombre("%" + buscar + "%", 0);
		}else {
			log.info("Buscar todos los grados");
			return gradoCrudRepository.findByEliminadoOrderByNombreDesc(0);
		}		
	}

	@Override
	public Grado findById(int id) {
		log.info("Buscando grado id:" + id);
		if(id <= 0) {
			log.info("No hay registro con ese id");
			return null;
		}		
		Grado g = gradoCrudRepository.findByGradoId(id); 
				
		return g;
	}

	@Override
	public int Guardar(Grado g) throws Exception {
		log.info("Guardar un grado");
		if(g == null) {		
			log.info("No se envio un objeto valido");
			throw new Exception("La informacion de grado no puede estar vacia");
		}
		gradoCrudRepository.save(g);
		
		return g.getGradoId();
	}

	@Override
	public void Eliminar(int id) {
		log.info("Eliminar un grado id:" + id);
		if(id > 0) {			
			log.info("Eliminado entidad");
			gradoCrudRepository.delete(new Grado(id));
			log.info("Se elimino correctamente");
		}else {
			log.info("No existe la entidad :" + id);			
		}				
	}

}
