package com.banco.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		}else
		{ 
			return "No Existe reporte para eliminar";
		}

	}

}

