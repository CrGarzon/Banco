package com.banco.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.entities.Cliente;
import com.banco.entities.Cuenta;
@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long>{
     
	 public Iterable<Cuenta> findAllBySaldo (double saldo);
	 public Iterable<Cuenta> findAllByEstado (boolean estado);
	 public Optional<Cuenta> findByCliente (Cliente cliente);

}
