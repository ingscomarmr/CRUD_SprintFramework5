package com.comr.crud.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="grado",schema="dbo")
public class Grado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grado_id")
	private Integer gradoId;
	
	private String nombre;
	
	@Column(name="usuario_id_mod")
	private Integer usuarioIdMod;
	
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="eliminado")
	private int eliminado;
	
	public Grado() {}
	
	public Grado(int id) {
		this.gradoId = id;
	}
	
	public Integer getGradoId() {
		return gradoId;
	}

	public void setGradoId(Integer gradoId) {
		this.gradoId = gradoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getUsuarioIdMod() {
		return usuarioIdMod;
	}

	public void setUsuarioIdMod(Integer usuarioIdMod) {
		this.usuarioIdMod = usuarioIdMod;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}
	
	
	
}
