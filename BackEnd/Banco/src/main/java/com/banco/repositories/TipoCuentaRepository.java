package com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banco.entities.TipoCuenta;

@Repository
public interface TipoCuentaRepository extends CrudRepository<TipoCuenta, Long>{
	//JPA
	public Iterable<TipoCuenta> findByNombre (String nombre);

	//SQL PROCEDURES
	@Procedure(name = TipoCuenta.SAVE_TIPOCUENTA)
	public String saveTipoCuentaSQL(@Param("P_ID") long id, @Param("P_NOMBRE") String nombre);

	@Procedure(name = TipoCuenta.UPDATE_TIPOCUENTA)
	public String updateTipoCuentaSQL(@Param("P_ID") long id, @Param("P_NOMBRE") String nombre);

	@Procedure(name = TipoCuenta.DELETE_TIPOCUENTA)
	public String deleteTipoCuentaSQL(@Param("P_ID") long id);

	//SQL FUNCTIONS
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_TIPOCUENTA_SERVICES.GET_TIPOCUENTAS)")
	public List<TipoCuenta> getTipoCuentaSQL();

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_TIPOCUENTA_SERVICES.GET_TIPOCUENTA_BY_ID(:P_ID)")
	public List<TipoCuenta> getTipoCuentaByIdSQL(@Param("P_ID") long id);
}
