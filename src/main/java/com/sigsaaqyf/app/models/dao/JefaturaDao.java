package com.sigsaaqyf.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Jefatura;

@Repository
public interface JefaturaDao extends CrudRepository<Jefatura, Long> {

}
