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
import com.banco.entities.Cliente;
import com.banco.entities.Cuenta;
import com.banco.entities.TipoCuenta;
import com.banco.extras.PrimaryKey;
import com.banco.repositories.ClienteRepository;
import com.banco.repositories.CuentaRepository;
import com.banco.repositories.TipoCuentaRepository;

@CrossOrigin
@RestController
public class CuentaService {

	@Autowired
	private ClienteRepository clienteRepositoryDAO;

	@Autowired
	private TipoCuentaRepository tipoCuentaRepositoryDAO;

	@Autowired
	private CuentaRepository cuentaRepositoryDAO;

	@RequestMapping(path="/deleteCuenta", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCuenta(@RequestParam long id){
		Optional<Cuenta> optrepor = cuentaRepositoryDAO.findById(id);
		if (optrepor.isPresent()) {
			cuentaRepositoryDAO.deleteById(id);
			return "la cuenta se elimino se elimino satisfactoriamente";
		}else { 
			return "No Existe cuenta para eliminar";
		}
	}

	@RequestMapping(path="/updateCuenta", method = RequestMethod.PUT)
	public  @ResponseBody String updateCuenta(
			@RequestParam long id,
			@RequestParam double saldo,
			@RequestParam boolean estado
			) {
		Optional<Cuenta> optcue = cuentaRepositoryDAO.findById(id);
		if (optcue.isPresent()) {
			Cuenta cuenta = new Cuenta();
			cuenta.setId(id);
			cuenta.setTipoCuenta(optcue.get().getTipoCuenta());
			cuenta.setCliente(optcue.get().getCliente());
			cuenta.setSaldo(saldo);
			cuenta.setEstado(estado);		
			cuentaRepositoryDAO.save(cuenta);
			return "Cuenta actualizada";
		}
		else {
			return "No existe una cuenta para actualizar";
		}

	}


	@RequestMapping(path="/saveCuenta", method = RequestMethod.POST)
	public @ResponseBody String saveCuenta(
			@RequestParam long id,
			@RequestParam long idTipoCuenta, 
			@RequestParam String tipoDocumento,
			@RequestParam String documento,
			@RequestParam double saldo,
			@RequestParam boolean estado
			) {
		Optional<Cuenta> optCue =  cuentaRepositoryDAO.findById(id);
		if(optCue.isPresent()) {
			return "La cuenta ya esxiste";
		}

		Optional<TipoCuenta> optTipoCuenta = tipoCuentaRepositoryDAO.findById(idTipoCuenta);
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);
		Optional<Cliente> optCliente = clienteRepositoryDAO.findById(pk);

		if(!optTipoCuenta.isPresent() || !optCliente.isPresent()) {
			return "El tipo cuenta o cliente no existen";
		}

		Optional<Cuenta> optUserCuenta = cuentaRepositoryDAO.findByCliente(optCliente.get());
		if(optUserCuenta.isPresent()) {
			return "Este usuario ya posee una cuenta";
		}
		if(!optCliente.get().isEstado()) {
			return "El cliente está inactivo para crear una cuenta"; 
		}

		Cuenta cuenta = new Cuenta();
		cuenta.setId(id);
		cuenta.setTipoCuenta(optTipoCuenta.get());
		cuenta.setCliente(optCliente.get());
		cuenta.setSaldo(saldo);
		cuenta.setEstado(estado);		
		cuentaRepositoryDAO.save(cuenta);
		return "La cuenta se ha guardado satisfactoriamente";
	}


	@RequestMapping (path="/getCuenta",method = RequestMethod.GET)
	public @ResponseBody Iterable <Cuenta> getCuenta(){
		return cuentaRepositoryDAO.findAll();
	}

	@RequestMapping (path="/getCuentaSaldo", method = RequestMethod.GET)
	public @ResponseBody Iterable <Cuenta> getCuentaSaldo(@RequestParam double saldo){
		return cuentaRepositoryDAO.findAllBySaldo(saldo);

	}

	@RequestMapping (path="/getCuentaByEstado", method = RequestMethod.GET)
	public @ResponseBody Iterable<Cuenta> getCuentaByEstado(@RequestParam boolean estado){
		return cuentaRepositoryDAO.findAllByEstado(estado);
	}

	@RequestMapping (path="/getCuentaById", method = RequestMethod.GET)
	public @ResponseBody Optional<Cuenta> getCuentaById(@RequestParam long id){
		Optional<Cuenta> optCuenta = cuentaRepositoryDAO.findById(id);
		if(optCuenta.isPresent()) {			
			return cuentaRepositoryDAO.findById(id);
		}
		return null;
	}
	
	@RequestMapping (path="/getCuentaByCliente", method = RequestMethod.GET)
	public @ResponseBody Optional<Cuenta> getCuentaByCliente(@RequestParam String tipoDocumento, @RequestParam String documento){
		PrimaryKey pk = new PrimaryKey();
		pk.setDocumento(documento);
		pk.setTipoDocumento(tipoDocumento);
		
		Optional<Cliente> optCliente = clienteRepositoryDAO.findById(pk);
		Optional<Cuenta> optCuenta = cuentaRepositoryDAO.findByCliente(optCliente.get());
		if(optCuenta.isPresent()) {			
			return optCuenta;
		}
		return null;
	}

	//SQL Services Procedimientos
	@RequestMapping(path="/saveCuentaSQL", method = RequestMethod.POST)
	public @ResponseBody String saveCuentaSQL(@RequestParam long id, @RequestParam double saldo, @RequestParam boolean estado,@RequestParam String documento,
			@RequestParam String tipo_documento, @RequestParam String id_tipo_cuenta) {
		return cuentaRepositoryDAO.saveCuentaSQL(id, saldo, estado, documento, tipo_documento, id_tipo_cuenta);
	}

	@RequestMapping(path="/updateCuentaSQL", method = RequestMethod.PUT)
	public @ResponseBody String updateCuentaSQL(@RequestParam long id, @RequestParam double saldo, @RequestParam boolean estado,@RequestParam String documento,
			@RequestParam String tipo_documento, @RequestParam String id_tipo_cuenta) {
		return cuentaRepositoryDAO.updateCuentaSQL(id, saldo, estado, documento, tipo_documento, id_tipo_cuenta);
	}

	@RequestMapping(path="/deleteCuentaSQL", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCuentaSQL(@RequestParam long id) {
		return cuentaRepositoryDAO.deleteCuentaSQL(id);
	}

	//SQL Services Funciones
	@RequestMapping (path="/getCuentaSQL", method =RequestMethod.GET)
	public @ResponseBody List<Cuenta> getCuentaSQL(){
		return cuentaRepositoryDAO.getCuentaSQL();
	}

	@RequestMapping (path="/getCuentaByIdSQL", method = RequestMethod.GET)
	public @ResponseBody List<Cuenta> getCuentaByIdSQL(@RequestParam long id) {
		return cuentaRepositoryDAO.getCuentaByIdSQL(id);

	}
	@RequestMapping (path="/getCuentaByEstadoSQL", method = RequestMethod.GET)
	public @ResponseBody List<Cuenta> getCuentaByEstadoSQL(@RequestParam boolean estado) {
		return cuentaRepositoryDAO.getCuentaByEstadoSQL(estado);		
	}
	@RequestMapping (path="/getCuentaBySaldoSQL", method = RequestMethod.GET)
	public @ResponseBody List<Cuenta> getCuentaBySaldoSQL(@RequestParam double saldo) {
		return cuentaRepositoryDAO.getCuentaBySaldoSQL(saldo);
	}

}
