package com.banco.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banco.entities.Reportes;

@Repository
public interface ReportesRepository extends CrudRepository<Reportes, Long>{
	//JPA
	public Iterable<Reportes> findByTipoMovimiento(String tipoMovimiento);

	//SQL PROCEDURES
	@Procedure(name = Reportes.SAVE_REPORTES)
	public String saveReportesSQL(
			@Param("P_ID") long id, 
			@Param("P_CANTIDAD") String cantidad, 
			@Param("P_FECHA") Date fecha,
			@Param("P_TIPO_MOVIMIENTO") String tipo_movimiento,
			@Param("P_DOCUMENTO") String documento, 
			@Param("P_TIPO_DOCUMENTO") String tipo_documento
			);

	@Procedure(name = Reportes.UPDATE_REPORTES)
	public String updateReportesQL(
			@Param("P_ID") long id, 
			@Param("P_CANTIDAD") String cantidad, 
			@Param("P_FECHA") Date fecha,
			@Param("P_TIPO_MOVIMIENTO") String tipo_movimiento,
			@Param("P_DOCUMENTO") String documento, 
			@Param("P_TIPO_DOCUMENTO") String tipo_documento
			);

	@Procedure(name = Reportes.DELETE_REPORTES)
	public String deleteReportesSQL(@Param("P_ID") long id);

	//SQL FUNCTIONS
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_REPORTES_SERVICES.GET_REPORTES)")
	public List<Reportes> getReportesSQL();

	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(PAQUETE_REPORTES_SERVICES.GET_REPORTES_BY_ID(:P_ID)")
	public List<Reportes> getReportesByIdSQL(@Param("P_ID") long id);
}
