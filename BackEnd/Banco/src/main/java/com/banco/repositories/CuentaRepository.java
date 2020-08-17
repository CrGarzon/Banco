package com.banco.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banco.entities.Cliente;
import com.banco.entities.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long>{
	//JPA   
	public Iterable<Cuenta> findAllBySaldo (double saldo);
	public Iterable<Cuenta> findAllByEstado (boolean estado);
	public Optional<Cuenta> findByCliente (Cliente cliente);

	//SQL PROCEDURES
	@Procedure(name = Cuenta.SAVE_CUENTA)
	public String saveCuentaSQL(
			@Param("P_ID") long id, 
			@Param("P_SALDO") double saldo, 
			@Param("P_ESTADO") boolean estado,
			@Param("P_DOCUMENTO") String documento,
			@Param("P_TIPO_DOCUMENTO") String tipo_documento, 
			@Param("P_ID_TIPO_CUENTA") String id_tipo_cuenta
			);

	@Procedure(name = Cuenta.UPDATE_CUENTA)
	public String updateCuentaSQL(
			@Param("P_ID") long id, 
			@Param("P_SALDO") double saldo, 
			@Param("P_ESTADO") boolean estado,
			@Param("P_DOCUMENTO") String documento,
			@Param("P_TIPO_DOCUMENTO") String tipo_documento, 
			@Param("P_ID_TIPO_CUENTA") String id_tipo_cuenta
			);

	@Procedure(name = Cuenta.DELETE_CUENTA)
	public String deleteCuentaSQL(@Param("P_ID") long id);

	//SQL FUNCTIONS
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CUENTA_SERVICES.GET_CUENTAS)")
	public List<Cuenta> getCuentaSQL();

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CUENTA_SERVICES.GET_CUENTA_BY_ID(:P_ID)")
	public List<Cuenta> getCuentaByIdSQL(@Param("P_ID") long id);

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CUENTA_SERVICES.GET_CUENTA_BY_ESTADO(:P_ESTADO)")
	public List<Cuenta> getCuentaByEstadoSQL(@Param("P_ESTADO") boolean estado);

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_CUENTA_SERVICES.GET_CUENTA_BY_SALDO(:P_SALDO)")
	public List<Cuenta> getCuentaBySaldoSQL(@Param("P_SALDO") double saldo);

}
