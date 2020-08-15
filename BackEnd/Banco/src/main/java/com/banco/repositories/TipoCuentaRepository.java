package com.banco.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.entities.TipoCuenta;

@Repository
public interface TipoCuentaRepository extends CrudRepository<TipoCuenta, Long>{
	 
	 public Iterable<TipoCuenta> findByNombre (String nombre);

}
