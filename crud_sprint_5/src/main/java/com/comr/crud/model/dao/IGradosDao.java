package com.comr.crud.model.dao;

import java.util.List;

import com.comr.crud.model.entity.Grado;

public interface IGradosDao {
	public List<Grado> getAll();
	public void save(Grado d);
	public void delete(Grado d);
}
