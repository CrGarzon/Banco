package com.banco.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedStoredProcedureQuery(
		name = Administrador.SAVE_ADMINISTRADOR,
		procedureName = "PAQUETE_ADMINISTRADOR_SERVICES.SAVE_ADMINISTRADOR",
		resultClasses = Administrador.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_NOMBRE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPODOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CLAVE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Administrador.UPDATE_ADMINISTRADOR,
		procedureName = "PAQUETE_ADMINISTRADOR_SERVICES.UPDATE_ADMINISTRADOR",
		resultClasses = Administrador.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_NOMBRE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPODOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CLAVE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Administrador.DELETE_ADMINISTRADOR,
		procedureName = "PAQUETE_ADMINISTRADOR_SERVICES.DELETE_ADMINISTRADOR",
		resultClasses =Administrador.class,
		parameters = {
				@StoredProcedureParameter(name = "P_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@Entity
@Table(name="ADMINISTRADOR")
public class Administrador {

	//Procedimientos
	public static final String SAVE_ADMINISTRADOR = "save_administrador";
	public static final String UPDATE_ADMINISTRADOR = "update_administrador";
	public static final String DELETE_ADMINISTRADOR = "delete_administrador";

	@Id
	private long id;

	@NotNull
	private String nombre;

	@NotNull
	private String tipoDocumento;

	@NotNull
	private String documento;

	@NotNull
	private String clave;

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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
