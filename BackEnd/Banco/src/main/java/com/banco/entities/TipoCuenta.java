package com.banco.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@NamedStoredProcedureQuery(
		name = TipoCuenta.SAVE_TIPOCUENTA,
		procedureName = "PAQUETE_TIPOCUENTA_SERVICES.SAVE_TIPOCUENTA",
		resultClasses = TipoCuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_NOMBRE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = TipoCuenta.UPDATE_TIPOCUENTA,
		procedureName = "PAQUETE_TIPOCUENTA_SERVICES.UPDATE_TIPOCUENTA",
		resultClasses = TipoCuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_NOMBRE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = TipoCuenta.DELETE_TIPOCUENTA,
		procedureName = "PAQUETE_TIPOCUENTA_SERVICES.DELETE_TIPOCUENTA",
		resultClasses = TipoCuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@Entity
@Table(name="TIPOCUENTA")
public class TipoCuenta {

	//Procedimientos
	public static final String SAVE_TIPOCUENTA = "save_tipocuenta";
	public static final String UPDATE_TIPOCUENTA = "update_tipocuenta";
	public static final String DELETE_TIPOCUENTA = "delete_tipocuenta";	

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