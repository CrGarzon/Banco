package com.banco.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.entities.Administrador;

@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long>{
}
