package com.banco.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.banco.extras.PrimaryKey;

import javax.persistence.FetchType;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	@EmbeddedId
	private PrimaryKey id;
	
	private String clave;
	
	private boolean estado;
	
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false) 
	private Cuenta cuenta;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reportes> reportes = new ArrayList<Reportes>();

	public PrimaryKey getId() {
		return id;
	}

	public void setId(PrimaryKey id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Reportes> getReportes() {
		return reportes;
	}

	public void setReportes(List<Reportes> reportes) {
		this.reportes = reportes;
	}

}
