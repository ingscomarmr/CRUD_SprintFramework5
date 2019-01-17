package com.comr.crud.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.comr.crud.model.entity.Grado;

//El dao lo impĺementamos y lo decoramos con Repository para poder usar
@Repository
public class GradoDaoImpl implements IGradosDao {
	
	private static final Log log = LogFactory.getLog(GradoDaoImpl.class);
	
	//la persistencia se declara para poder usar las consultas en base de datos
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Grado> getAll() {	
		log.info("Consultar todos los grados");
		try {
			//realizando un query con Criterial
			CriteriaBuilder builder = em.getCriteriaBuilder();
			
			CriteriaQuery<Grado> criteria = builder.createQuery(Grado.class);
			
			Root<Grado> gradoRoot = criteria.from(Grado.class);
			
			criteria.select(gradoRoot)
					.where(builder.equal(gradoRoot.get("eliminado"), 0));
			
			return em.createQuery(criteria).getResultList();
		}catch(Exception ex) {
			log.error("ERROR AL CONSULTAR METODO getAll", ex);
			throw ex;
		}
		
	}

	@Transactional(readOnly=false)
	@Override
	public int save(Grado grado) {
		try {
			log.info("Guardar Grado:" + grado.getNombre());	
			if(grado != null && grado.getGradoId() != null &&  grado.getGradoId() > 0) { //update
				em.merge(grado);
			}else { //insert
				em.persist(grado);		
				em.flush(); //para que le asigne el id
			}			
			return grado.getGradoId();
		}catch(Exception ex) {
			log.error("ERROR AL GUARDAR GRADO", ex);			
			throw ex;
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(Grado grado) {
		try {
			log.info("Eliminar Grado:" + grado.getNombre());				
			em.remove(em.merge(grado));			
		}catch(Exception ex) {
			log.error("ERROR AL ELIMINAR GRADO", ex);			
			throw ex;
		}
	}

	@Override
	public Grado findById(int id) {
		log.info("Obtener Gradi Id:" + id);
		try {
			//realizando un query con Criterial
			CriteriaBuilder builder = em.getCriteriaBuilder();
			
			CriteriaQuery<Grado> criteria = builder.createQuery(Grado.class);
			
			Root<Grado> gradoRoot = criteria.from(Grado.class);
			
			//formando el query
			criteria.select(gradoRoot)
					.where(
							builder.equal(gradoRoot.get("eliminado"), 0),
							builder.equal(gradoRoot.get("gradoId"), id)
							);
			List<Grado> gradoList = em.createQuery(criteria).getResultList();
			if(gradoList != null && gradoList.size() > 0) {
				return gradoList.get(0);
			}
			return null;
			
		}catch(Exception ex) {
			log.error("ERROR AL CONSULTAR METODO findById", ex);
			throw ex;
		}
	}

}
