package com.banco.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="CUENTA")
public class Cuenta {
	
	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_cuenta")
	private TipoCuenta tipoCuenta;

	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "tipoDocumento", insertable = true, updatable = true),
		@JoinColumn(name = "documento", insertable = true, updatable = true),
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
