package com.banco.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(
		name = Reportes.SAVE_REPORTES,
		procedureName = "PAQUETE_REPORTES_SERVICES.SAVE_REPORTES",
		resultClasses = Reportes.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CANTIDAD", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_FECHA", type = Date.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_MOVIMIENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Reportes.UPDATE_REPORTES,
		procedureName = "PAQUETE_REPORTES_SERVICES.UPDATE_REPORTES",
		resultClasses = Reportes.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CANTIDAD", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_FECHA", type = Date.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_MOVIMIENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Reportes.DELETE_REPORTES,
		procedureName = "PAQUETE_REPORTES_SERVICES.DELETE_REPORTES",
		resultClasses = Reportes.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@Entity
@Table(name="REPORTES")

public class Reportes {

	//Procedimientos
	public static final String SAVE_REPORTES = "save_reportes";
	public static final String UPDATE_REPORTES = "update_reportes";
	public static final String DELETE_REPORTES = "delete_reportes";	

	@Id
	private long id;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "documento", insertable = true, updatable = true),
		@JoinColumn(name = "tipoDocumento", insertable = true, updatable = true)
	})
	private Cliente cliente;
	
	private String tipoMovimiento;
	
	private double cantidad;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

}

