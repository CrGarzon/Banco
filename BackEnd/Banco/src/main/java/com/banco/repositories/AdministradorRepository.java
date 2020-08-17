package com.banco.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banco.entities.Administrador;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long>{
	//JPA
	public Optional<Administrador> findByTipoDocumentoAndDocumento(String tipoDocumento, String documento);
	
	//SQL PROCEDURES
	@Procedure(name = Administrador.SAVE_ADMINISTRADOR)
	public String saveAministradorSQL(
			@Param("P_ID") long id, 
			@Param("P_NOMBRE") String nombre,
			@Param("P_TIPODOCUMENTO") String tipodocumento, 
			@Param("P_DOCUMENTO") String documento, 
			@Param ("P_CLAVE") String clave 
			);

	@Procedure(name = Administrador.UPDATE_ADMINISTRADOR)
	public String updateAministradorSQL(
			@Param("P_ID") long id, 
			@Param("P_NOMBRE") String nombre,
			@Param("P_TIPODOCUMENTO") String tipodocumento, 
			@Param("P_DOCUMENTO") String documento, 
			@Param ("P_CLAVE") String clave 
			);

	@Procedure(name = Administrador.DELETE_ADMINISTRADOR)
	public String deleteAdministradorSQL(@Param("P_ID") long id);

	//SQL FUNCTIONS
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_ADMINISTRADOR_SERVICES.GET_ADMINISTRADOR)")
	public List<Administrador> getAdministradorSQL();

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_ADMINISTRADOR_SERVICES.GET_ADMINISTRADOR_BY_ID(:P_ID)")
	public List<Administrador> getAdministradorByIdSQL(@Param("P_ID") long id);
}

