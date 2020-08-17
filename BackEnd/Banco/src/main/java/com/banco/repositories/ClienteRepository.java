package com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banco.entities.Cliente;
import com.banco.extras.PrimaryKey;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, PrimaryKey> {
	//JPA
	public Iterable<Cliente> findAllByEstado (boolean estado);

	//SQL PROCEDURES
	@Procedure(name = Cliente.SAVE_CLIENTE)
	public String saveClienteSQL(
			@Param("P_DOCUMENTO") String documento, 
			@Param("P_TIPO_DOCUMENTO") String tipodocumento,
			@Param("P_CLAVE") String clave, 
			@Param("P_ESTADO") String estado
			);

	@Procedure(name = Cliente.UPDATE_CLIENTE)
	public String updateClienteSQL(
			@Param("P_DOCUMENTO") String documento, 
			@Param("P_TIPO_DOCUMENTO") String tipodocumento,
			@Param("P_CLAVE") String clave, 
			@Param("P_ESTADO") String estado
			);

	@Procedure(name = Cliente.DELETE_CLIENTE)
	public String deleteClienteSQL(@Param("P_DOCUMENTO") String documento, @Param("P_TIPO_DOCUMENTO") String tipodocumento);

	//SQL FUNCTIONS
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CLIENTE_SERVICES.GET_CLIENTE)")
	public List<Cliente> getClienteSQL();

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CLIENTE_SERVICES.GET_CLIENTE_BY_ID(:P_DOCUMENTO, :P_TIPO_DOCUMENTO)")
	public List<Cliente> getClienteByIdSQL(@Param ("P_DOCUMENTO") String documento, @Param("P_TIPO_DOCUMENTO") String tipodocumento);

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CLIENTE_SERVICES.GET_CLIENTE_BY_ESTADO(:P_ESTADO)")
	public List<Cliente> getClienteByEstadoSQL(@Param("P_ESTADO") String estado);

}


