package com.banco.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="TIPOCUENTA")
public class TipoCuenta {

	@Id
	private long id;
	
	private String nombre;
	
	@JsonBackReference
	@OneToMany(mappedBy = "tipoCuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	private List<Cuenta> cuenta = new ArrayList<Cuenta>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}