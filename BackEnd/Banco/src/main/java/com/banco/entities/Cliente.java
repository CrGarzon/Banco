package com.banco.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import com.banco.extras.PrimaryKey;
import javax.persistence.FetchType;
import javax.persistence.NamedStoredProcedureQuery;

@NamedStoredProcedureQuery(
		name = Cliente.SAVE_CLIENTE,
		procedureName = "PAQUETE_CLIENTE_SERVICES.SAVE_CLIENTE",
		resultClasses = Cliente.class,
		parameters = {
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CLAVE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ESTADO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Cliente.UPDATE_CLIENTE,
		procedureName = "PAQUETE_CLIENTE_SERVICES.UPDATE_CLIENTE",
		resultClasses =Cliente.class,
		parameters = {
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_CLAVE", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_ESTADO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@NamedStoredProcedureQuery(
		name = Cliente.DELETE_CLIENTE,
		procedureName = "PAQUETE_CLIENTE_SERVICES.DELETE_CLIENTE",
		resultClasses = Cliente.class,
		parameters = {
				@StoredProcedureParameter(name = "P_DOCUMENTO", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_TIPO_DOCUMENTO", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "RESPUESTA", type = String.class, mode = ParameterMode.OUT)
		}
		)

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	//Procedimientos
		public static final String SAVE_CLIENTE = "save_cliente";
		public static final String UPDATE_CLIENTE = "update_cliente";
		public static final String DELETE_CLIENTE = "delete_cliente";	
	
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

}
