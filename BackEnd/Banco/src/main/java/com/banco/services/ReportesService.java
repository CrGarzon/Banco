package com.banco.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.banco.entities.Cliente;
import com.banco.entities.Reportes;
import com.banco.extras.PrimaryKey;
import com.banco.repositories.ClienteRepository;
import com.banco.repositories.ReportesRepository;

@CrossOrigin
@RestController
public class ReportesService {

	@Autowired
	private ClienteRepository clienteRepositoryDAO;

	@Autowired
	private ReportesRepository reportesRepositoryDAO;

	@RequestMapping (path="/getReportes", method = RequestMethod.GET)
	public @ResponseBody Iterable <Reportes> getReportes (){
		return reportesRepositoryDAO.findAll();
	}

	@RequestMapping (path="/getReportesByid", method = RequestMethod.GET)
	public @ResponseBody Optional<Reportes> getReportesByid(@RequestParam long id){
		return reportesRepositoryDAO.findById(id);
	}
	
	@RequestMapping (path="/getReportesByMovimiento", method = RequestMethod.GET)
	public @ResponseBody Iterable<Reportes> getReportesByMovimiento(@RequestParam String tipoMovimiento){
		return reportesRepositoryDAO.findByTipoMovimiento(tipoMovimiento);
	}

	@RequestMapping(path="/saveReportes", method = RequestMethod.POST)
	public @ResponseBody String saveReportes(
			@RequestParam long id,
			@RequestParam Date fecha, 
			@RequestParam String tipoDocumento,
			@RequestParam String documento,
			@RequestParam String tipoMovimimiento,
			@RequestParam double cantidad
			) {
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);
		Optional<Cliente> optcliente = clienteRepositoryDAO.findById(pk);

		if(!optcliente.isPresent()) {
			return "el cliente no existe";
		}

		Optional<Reportes> optCue =  reportesRepositoryDAO.findById(id);
		if(optCue.isPresent()) {
			return "El reportes ya existe";
		}
		Reportes reporte = new Reportes();
		reporte.setId(id);
		reporte.setFecha(fecha);
		reporte.setCliente(optcliente.get());
		reporte.setTipoMovimiento(tipoMovimimiento);
		reporte.setCantidad(cantidad);

		reportesRepositoryDAO.save(reporte);
		return "El reporte se  ha guardado satisfactoriamente";

	}

	@RequestMapping(path="/updateReportes", method = RequestMethod.PUT)
	public @ResponseBody String updateReportes(
			@RequestParam long id,
			@RequestParam Date fecha, 
			@RequestParam String tipoDocumento,
			@RequestParam String documento,
			@RequestParam String tipoMovimimiento,
			@RequestParam double cantidad
			) {
		Optional<Reportes> optrepor = reportesRepositoryDAO.findById(id);
		if (optrepor.isPresent()) {
			Reportes reporte = new Reportes();
			reporte.setId(id);
			reporte.setFecha(fecha);
			reporte.setCliente(optrepor.get().getCliente());
			reporte.setCantidad(cantidad);
			return "El reporte fue Actualizado";
		}
		else {
			return "No existe el reporte para actualizar";
		}

	}


	@RequestMapping(path="/deleteReportes", method = RequestMethod.DELETE)
	public @ResponseBody String deleteReportes(@RequestParam long id){
		Optional<Reportes> optrepor = reportesRepositoryDAO.findById(id);
		if (optrepor.isPresent()) {
			reportesRepositoryDAO.deleteById(id);
			return "el reporte se elimino se elimino satisfactoriamente";
		}
		else{ 
			return "No Existe reporte para eliminar";
		}

	}

	//SQL Services Procedimientos
	@RequestMapping(path="/saveReportesSQL", method = RequestMethod.POST)
	public @ResponseBody String saveReportesSQL(@RequestParam("P_ID") long id, @RequestParam("P_CANTIDAD") String cantidad,
			@RequestParam("P_FECHA") Date fecha, @RequestParam("P_TIPO_MOVIMIENTO") String tipo_movimiento,@RequestParam("P_DOCUMENTO") String documento, @RequestParam("P_TIPO_DOCUMENTO") String tipo_documento) {
		return reportesRepositoryDAO.saveReportesSQL(id, cantidad, fecha, tipo_movimiento, documento, tipo_documento);
	}

	@RequestMapping(path="/updateReportesSQL", method = RequestMethod.PUT)
	public @ResponseBody String uptadeReportesSQL(@RequestParam("P_ID") long id, @RequestParam("P_CANTIDAD") String cantidad,
			@RequestParam("P_FECHA") Date fecha, @RequestParam("P_TIPO_MOVIMIENTO") String tipo_movimiento,@RequestParam("P_DOCUMENTO") String documento, @RequestParam("P_TIPO_DOCUMENTO") String tipo_documento) {
		return reportesRepositoryDAO.updateReportesQL(id, cantidad, fecha, tipo_movimiento, documento, tipo_documento);
	}

	@RequestMapping(path="/deleteReportesSQL", method = RequestMethod.DELETE)
	public @ResponseBody String deleteReportesSQL(@RequestParam long id) {
		return reportesRepositoryDAO.deleteReportesSQL(id);
	}

	//SQL Services Funciones
	@RequestMapping (path="/getReportesSQL", method =RequestMethod.GET)
	public @ResponseBody List<Reportes> getReportesSQL(){
		return reportesRepositoryDAO.getReportesSQL();
	}

	@RequestMapping (path="/getIdReportesByIdSQL", method = RequestMethod.GET)
	public @ResponseBody List<Reportes> getIdReportesByIdSQL(@RequestParam long id) {
		return reportesRepositoryDAO.getReportesByIdSQL(id);
	}

}
