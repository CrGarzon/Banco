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
import com.banco.repositories.CuentaRepository;
import com.banco.entities.Cliente;
import com.banco.entities.Cuenta;
import com.banco.extras.PrimaryKey;
import com.banco.repositories.ClienteRepository;

@CrossOrigin
@RestController
public class ClienteService  {

	@Autowired
	private CuentaRepository cuentaRepositoryDAO;

	@Autowired
	private ClienteRepository clienteRepositoryDAO;

	@RequestMapping (path="/getClienteByEstado", method = RequestMethod.GET)
	public @ResponseBody Iterable<Cliente> getClienteByEstado(@RequestParam boolean estado){
		return clienteRepositoryDAO.findAllByEstado(estado);
	}

	@RequestMapping (path="/getCliente",method = RequestMethod.GET)
	public @ResponseBody Iterable <Cliente> getCliente(){
		return clienteRepositoryDAO.findAll();
	}

	@RequestMapping(path = "/getClienteById", method = RequestMethod.GET)
	public @ResponseBody Optional<Cliente> getClienteById(@RequestParam String tipoDocumento, @RequestParam String documento){
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);
		Optional<Cliente> optCliente = clienteRepositoryDAO.findById(pk);
		if(optCliente.isPresent()) {
			return clienteRepositoryDAO.findById(pk);			
		}
		return null;
	}

	@RequestMapping(path="/saveCliente", method = RequestMethod.POST)
	public @ResponseBody String saveCliente(
			@RequestParam String tipodocumento,
			@RequestParam String documento,
			@RequestParam String clave, 
			@RequestParam boolean estado
			) {
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipodocumento);

		Optional<Cliente> optAdm =  clienteRepositoryDAO.findById(pk);
		if(!optAdm.isPresent()) {
			Cliente pcliente = new Cliente();
			pcliente.setId(pk);
			pcliente.setClave(clave);
			pcliente.setEstado(estado);
			clienteRepositoryDAO.save(pcliente);
			return "Se registro el usurio";
		}
		else {
			return"No se puede registrar";
		}
	}

	@RequestMapping(path="/deleteCliente", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCliente(
			@RequestParam String tipoDocumento,
			@RequestParam String documento
			) {
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);

		Optional<Cliente> optCliente =  clienteRepositoryDAO.findById(pk);
		if(!optCliente.isPresent()) {
			return "no hay cliente";
		}
		Optional<Cuenta> optCuenta = cuentaRepositoryDAO.findByCliente(optCliente.get());
		if(optCuenta.isPresent() ) {
			cuentaRepositoryDAO.deleteById(optCuenta.get().getId());	
		}
		clienteRepositoryDAO.deleteById(pk);
		return "Se ha eliminado el Cliente";
	}

	@RequestMapping(path="/updateCliente", method = RequestMethod.PUT)
	public @ResponseBody String updateCliete(
			@RequestParam String tipoDocumento,
			@RequestParam String documento, 
			@RequestParam String clave,
			@RequestParam boolean estado
			) {
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);

		Optional<Cliente> optAdm =  clienteRepositoryDAO.findById(pk);
		if(optAdm.isPresent()) {
			Cliente pcliente = new Cliente();
			pcliente.setId(pk);
			pcliente.setClave(clave);
			pcliente.setEstado(estado);
			clienteRepositoryDAO.save(pcliente);
			return "Se actualizo el usuario";
		}
		else {
			return"No existe el usurio para actualizar";
		}
	}
	//SQL Services Procedimientos
	@RequestMapping(path="/saveClienteSQL", method = RequestMethod.POST)
	public @ResponseBody String saveClienteSQL(@RequestParam String documento, @RequestParam String tipodocumento,
			@RequestParam String clave, @RequestParam String estado) {
		return clienteRepositoryDAO.saveClienteSQL(documento, tipodocumento, clave, estado);
	}

	@RequestMapping(path="/updateClienteSQL", method = RequestMethod.PUT)
	public @ResponseBody String updateClienteSQL(@RequestParam String documento, @RequestParam String tipodocumento,
			@RequestParam String clave, @RequestParam String estado) {
		return clienteRepositoryDAO.updateClienteSQL(documento, tipodocumento, clave, estado);
	}

	@RequestMapping(path="/deleteClienteSQL", method = RequestMethod.DELETE)
	public @ResponseBody String deleteClienteSQL(@RequestParam String documento, @RequestParam String tipodocumento) {
		return clienteRepositoryDAO.deleteClienteSQL(documento, tipodocumento);
	}

	//SQL Services Funciones
	@RequestMapping (path="/getClienteSQL", method =RequestMethod.GET)
	public @ResponseBody List<Cliente> getClienteSQL(){
		return clienteRepositoryDAO.getClienteSQL();
	}

	@RequestMapping (path="/getClienteByIdSQL", method = RequestMethod.GET)
	public @ResponseBody List<Cliente> getClienteByIdSQL(@RequestParam String documento, @RequestParam String tipodocumento) {
		return clienteRepositoryDAO.getClienteByIdSQL(documento, tipodocumento);
	}

	@RequestMapping (path="/getClienteByEstadoSQL", method =RequestMethod.GET)
	public @ResponseBody List<Cliente> getClienteByEstadoSQL(@RequestParam String estado){
		return clienteRepositoryDAO.getClienteByEstadoSQL(estado);
	}

}
