package com.comr.crud.repository;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.comr.crud.model.entity.Grado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradoCrudRepositoryTest {

	private static final Log log = LogFactory.getLog(GradoCrudRepositoryTest.class);
			
	@Autowired
	@Qualifier("gradoCrudRepository")
	GradoCrudRepository gradoCrudRepository;
	
	@Ignore
	@Test
	public void getAllTest() {
		log.info("INICIANDO TEST getAllTest");
		int testVal = 0;
		List<Grado> gradoList = gradoCrudRepository.findAll();
		for (Grado grado : gradoList) {
			log.info("Grado:" + grado.getNombre());			
		}
		
		assertTrue(gradoList.size() > testVal);
		
	}
	
	@Ignore
	@Test
	public void saveTest() {
		log.info("INICIANDO TEST save");
		String nombre="TEST GRADO";
		
		Grado g = new Grado();
		g.setUsuarioIdMod(1);
		g.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		g.setNombre(nombre);		
		g.setEliminado(0);
		
		gradoCrudRepository.save(g);
		
		log.info("ID DEL GRADO ACTUAL:" + g.getGradoId());
		assertTrue(g.getGradoId() > 0);
	}
	
	@Ignore
	@Test
	public void updateTest() {
		log.info("INICIANDO TEST update");			
		Grado g = gradoCrudRepository.findByGradoId(1);
		long dateTime = System.currentTimeMillis();
		g.setFechaModificacion(new Timestamp(dateTime));			
		gradoCrudRepository.save(g);
		g = gradoCrudRepository.findByGradoId(1);		
		
		assertTrue(dateTime == g.getFechaModificacion().getTime());
	}
	
	@Ignore
	@Test
	public void deleteTest() {
		log.info("INICIANDO TEST delete");
		int id = 12;
						
		log.info("ELIMINAR GRADO:" + id);
		Grado g = gradoCrudRepository.findByGradoId(id);
		if(g != null) {
			log.info("ELIMINAR GRADO:" + g.getNombre());
			gradoCrudRepository.delete(g);
		}else {
			log.info("Ya no existe el grado id:" + id);
		}
					
		g = gradoCrudRepository.findByGradoId(id);
		
		assertTrue(g == null);			
	}
	
	
	@Transactional(readOnly=false)
	@Test
	@Rollback(value=false)
	public void deleteByNombreTest() {
		String nombre = "TEST GRADO";
		log.info("INICIANDO TEST delete by name:" + nombre);
		
		int numAntes = 0;
		int numDespues = 0;
		
		List<Grado> gList = gradoCrudRepository.findByNombreOrderByNombre(nombre);
		numAntes = (gList != null? gList.size() : 0);
		for (Grado grado : gList) {
			log.info("Grado:[" + grado.getNombre() + "]");
		}
		log.info("Eliminar ahora todos los que tengan:[" + nombre + "]");
		gradoCrudRepository.deleteByNombre(nombre);
		
		gList = gradoCrudRepository.findByNombreOrderByNombre(nombre);
		numDespues = (gList != null? gList.size() : 0);
		
		for (Grado grado : gList) {
			log.info("Grado:[" + grado.getNombre() + "]");
		}
		
		assertTrue(numAntes > numDespues);
		
	}
}
