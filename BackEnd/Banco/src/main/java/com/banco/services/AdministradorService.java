package com.banco.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.banco.entities.Administrador;
import com.banco.repositories.AdministradorRepository;

@RestController
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepositoryDAO;

	@RequestMapping (path="/getAdministrador", method = RequestMethod.GET)
	public @ResponseBody Iterable<Administrador> getadministrador(){
		return administradorRepositoryDAO.findAll();
	}

	@RequestMapping (path="/getAdministradorByid", method = RequestMethod.GET)
	public @ResponseBody Optional <Administrador> getAdministradorByid(@RequestParam long id){
		Optional<Administrador> optAdmin = administradorRepositoryDAO.findById(id);
		if(optAdmin.isPresent()) {			
			return administradorRepositoryDAO.findById(id);
		}
		return null;
	}

	@RequestMapping(path="/updateAdministrador", method = RequestMethod.PUT)
	public @ResponseBody String updateAdministrador(@RequestParam long id, @RequestParam String nombre, @RequestParam String tipoDocumento,
			@RequestParam String documento, @RequestParam String clave ) {
		Optional<Administrador> optAdm =  administradorRepositoryDAO.findById(id);
		if(optAdm.isPresent()) {
			Administrador p = new Administrador();
			p.setId(id);
			p.setNombre(nombre);
			p.setTipoDocumento(tipoDocumento);
			p.setClave(clave);
			administradorRepositoryDAO.save(p);

			return "Administrador actualizado";
		}
		else {
			return "No existe Administrador que actualizar";
		}

	}

	@RequestMapping(path="/saveAdministrador", method = RequestMethod.POST)
	public @ResponseBody String saveAdministrador(@RequestParam long id, @RequestParam String nombre, @RequestParam String tipoDocumento,
			@RequestParam String documento, @RequestParam String clave) {
		Optional<Administrador> optAdm =  administradorRepositoryDAO.findById(id);
		if(!optAdm.isPresent()) {
			Administrador p = new Administrador();
			p.setId(id);
			p.setNombre(nombre);
			p.setTipoDocumento(tipoDocumento);
			p.setClave(clave);
			administradorRepositoryDAO.save(p);

			return "Se registro el Administrador";
		}
		else {
			return "No se realizo el regitro";
		}

	}

	@RequestMapping(path="/deleteAdministrador", method = RequestMethod.DELETE)
	public @ResponseBody String deleteAdministrador(@RequestParam long id){
		Optional<Administrador> optAdmin = administradorRepositoryDAO.findById(id);
		if(optAdmin.isPresent()) {			
			administradorRepositoryDAO.deleteById(id);
			return "El Administrador se elimino satisfactoriamente";
		}
		return "El Administrador no existe";
	}

}
