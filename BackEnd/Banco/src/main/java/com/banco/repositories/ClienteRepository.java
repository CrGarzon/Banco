package com.banco.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.entities.Cliente;
import com.banco.extras.PrimaryKey;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, PrimaryKey> {
	
	public Iterable<Cliente> findAllByEstado (boolean estado);
	
}
