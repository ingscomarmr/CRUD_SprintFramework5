package com.comr.crud.model.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.comr.crud.model.entity.Grado;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GradoDaoImplTest {
			
	private static final Log log = LogFactory.getLog(GradoDaoImplTest.class);
	
	@Autowired
	//@Qualifier("areaTematicaJapRepository")
	private IGradosDao  gradoDao;
	
	@Test
	public void getAllTest() {
		log.info("INICIANDO TEST getAllTest");
		int testVal = 0;
		List<Grado> gradoList = gradoDao.getAll();
		for (Grado grado : gradoList) {
			log.info("Grado:" + grado.getNombre());			
		}
		
		assertTrue(gradoList.size() > testVal);
		
	}
	
	
	@Test
	public void saveTest() {
		log.info("INICIANDO TEST save");
		String nombre="TEST GRADO";
		
		Grado g = new Grado();
		g.setUsuarioIdMod(1);
		g.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		g.setNombre(nombre);		
		g.setEliminado(0);
		
		gradoDao.save(g);
		
		log.info("ID DEL GRADO ACTUAL:" + g.getGradoId());
		assertTrue(g.getGradoId() > 0);
	}
	
	@Test
	public void updateTest() {
		log.info("INICIANDO TEST update");			
		Grado g = gradoDao.findById(1);
		long dateTime = System.currentTimeMillis();
		g.setFechaModificacion(new Timestamp(dateTime));			
		gradoDao.save(g);
		g = gradoDao.findById(1);		
		
		assertTrue(dateTime == g.getFechaModificacion().getTime());
	}
	
	
	@Test
	public void deleteTest() {
		log.info("INICIANDO TEST delete");
		int id = 12;
						
		log.info("ELIMINAR GRADO:" + id);
		Grado g = gradoDao.findById(id);
		if(g != null) {
			log.info("ELIMINAR GRADO:" + g.getNombre());
			gradoDao.delete(g);
		}else {
			log.info("Ya no existe el grado id:" + id);
		}
					
		g = gradoDao.findById(id);
		
		assertTrue(g == null);
				
	}
	
}
