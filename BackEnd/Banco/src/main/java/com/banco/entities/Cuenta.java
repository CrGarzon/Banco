package com.banco.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQuery(
		name = Cuenta.SAVE_CUENTA,
		procedureName = "PAQUETE_CUENTA_SERVICES.SAVE_CUENTA",
		resultClasses = Cuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_SALDO", type = double.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ESTADO", type = boolean.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ID_TIPO_CUENTA", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Cuenta.UPDATE_CUENTA,
		procedureName = "PAQUETE_CUENTA_SERVICES.UPDATE_CUENTA",
		resultClasses = Cuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_SALDO", type = double.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ESTADO", type = boolean.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ID_TIPO_CUENTA", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Cuenta.DELETE_CUENTA,
		procedureName = "PAQUETE_CUENTA_SERVICES.DELETE_CUENTA",
		resultClasses = Cuenta.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@Entity
@Table(name="CUENTA")
public class Cuenta {

	//Procedimientos
	public static final String SAVE_CUENTA = "save_cuenta";
	public static final String UPDATE_CUENTA = "update_cuenta";
	public static final String DELETE_CUENTA = "delete_cuenta";

	@Id
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_tipo_cuenta")
	private TipoCuenta tipoCuenta;

	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "documento", insertable = true, updatable = true),
		@JoinColumn(name = "tipoDocumento", insertable = true, updatable = true)
	})
	private Cliente cliente;

	private double saldo;

	private boolean estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
