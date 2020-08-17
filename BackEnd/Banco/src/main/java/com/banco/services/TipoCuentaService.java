package com.banco.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.banco.entities.TipoCuenta;
import com.banco.repositories.TipoCuentaRepository;

@CrossOrigin
@RestController
public class TipoCuentaService {

	@Autowired
	private TipoCuentaRepository tipocuentaRepositoryDAO;

	//JPA Services
	@RequestMapping (path="/getTipoCuenta", method =RequestMethod.GET)
	public @ResponseBody Iterable <TipoCuenta> getTipoCuenta(){
		return tipocuentaRepositoryDAO.findAll();
	}

	@RequestMapping (path="/getIdTipoCuenta", method = RequestMethod.GET)
	public @ResponseBody Optional<TipoCuenta> getIdTipoCuenta(@RequestParam long id){
		return tipocuentaRepositoryDAO.findById(id);
	}

	@RequestMapping (path="/getTipoCuentaNombre", method = RequestMethod.GET)
	public @ResponseBody Iterable<TipoCuenta> getTipoCuentaNombre(@RequestParam String nombre){
		return tipocuentaRepositoryDAO.findByNombre(nombre);
	}

	@RequestMapping(path="/updateTipoCuenta", method = RequestMethod.PUT)
	public @ResponseBody String updateTipoCuenta(@RequestParam long id, @RequestParam String nombre) {
		Optional<TipoCuenta> optcue =  tipocuentaRepositoryDAO.findById(id);
		if(optcue.isPresent()) {
			TipoCuenta p = new TipoCuenta();
			p.setId(id);
			p.setNombre(nombre);
			tipocuentaRepositoryDAO.save(p);
			return "Se actualizao el tipo cuenta";
		}
		else {
			return "No se pudo actualizar";
		}
	}

	@RequestMapping(path="/saveTipoCuenta", method = RequestMethod.POST)
	public @ResponseBody String saveTipoCuenta(@RequestParam long id, @RequestParam String nombre) {
		Optional<TipoCuenta> optcue =  tipocuentaRepositoryDAO.findById(id);
		if(!optcue.isPresent()) {
			TipoCuenta p = new TipoCuenta();
			p.setId(id);
			p.setNombre(nombre);
			tipocuentaRepositoryDAO.save(p);
			return "Se Guardo exitosamente el tipo cuenta";
		}
		else {
			return "No se pudo guardar el tipo cuenta";
		}
	}

	@RequestMapping(path="/deleteTipoCuenta", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTipoCuenta(@RequestParam long id){
		tipocuentaRepositoryDAO.deleteById(id);
		return "La cuenta se elimino satisfactoriamente";
	}

	//SQL Services Procedimientos
	@RequestMapping(path="/saveTipoCuentaSQL", method = RequestMethod.POST)
	public @ResponseBody String saveTipoCuentaSQL(@RequestParam long id, @RequestParam String nombre) {
		return tipocuentaRepositoryDAO.saveTipoCuentaSQL(id, nombre);
	}

	@RequestMapping(path="/updateTipoCuentaSQL", method = RequestMethod.PUT)
	public @ResponseBody String updateTipoCuentaSQL(@RequestParam long id, @RequestParam String nombre) {
		return tipocuentaRepositoryDAO.updateTipoCuentaSQL(id, nombre);
	}

	@RequestMapping(path="/deleteTipoCuentaSQL", method = RequestMethod.DELETE)
	public @ResponseBody String deleteTipoCuentaSQL(@RequestParam long id) {
		return tipocuentaRepositoryDAO.deleteTipoCuentaSQL(id);
	}

	//SQL Services Funciones
	@RequestMapping (path="/getTipoCuentaSQL", method =RequestMethod.GET)
	public @ResponseBody List<TipoCuenta> getTipoCuentaSQL(){
		return tipocuentaRepositoryDAO.getTipoCuentaSQL();
	}

	@RequestMapping (path="/getTipoCuentaByIdSQL", method = RequestMethod.GET)
	public @ResponseBody List<TipoCuenta> getTipoCuentaByIdSQL(@RequestParam long id) {
		return tipocuentaRepositoryDAO.getTipoCuentaByIdSQL(id);
	}

}
