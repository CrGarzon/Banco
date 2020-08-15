package com.banco.extras;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PrimaryKey implements Serializable{

	private static final long serialVersionUID = 1L;

	
	
	public String tipoDocumento;
	public String documento;
	
	public PrimaryKey () {
		
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


}
