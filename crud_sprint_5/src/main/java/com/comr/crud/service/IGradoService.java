package com.comr.crud.service;

import java.util.List;
import com.comr.crud.model.entity.Grado;

public interface IGradoService {
	public List<Grado> getGradosToGrid(String buscar);
	public Grado findById(int id);
	public int Guardar(Grado g) throws Exception;
	public void Eliminar(int id);
}
