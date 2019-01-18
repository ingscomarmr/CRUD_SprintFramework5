package com.comr.crud.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comr.crud.model.entity.Grado;

//un Crud Repositorio es mas rapido de armar y no necesita de una interfas ni de mucho codigo pues ya trae metodos preconstruidos
@Repository("gradoCrudRepository")
public interface GradoCrudRepository extends CrudRepository<Grado, Integer>{
	//puedes describir consultas con tansolo ponder findBy
	List<Grado> findByNombreOrderByNombre(String nombre);
	List<Grado> findAll();
	Grado findByGradoId(int id);
	void deleteByNombre(String nombre);
}
