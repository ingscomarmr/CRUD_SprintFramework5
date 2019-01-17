package com.comr.crud.model.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comr.crud.model.entity.Grado;

@Repository("gradoRepository")
public interface GradoRepository extends JpaRepository<Grado, Serializable> {
	public List<Grado> findAll();
}
