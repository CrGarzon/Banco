package com.banco.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.banco.entities.Reportes;

@Repository
public interface ReportesRepository extends CrudRepository<Reportes, Long>{
}
