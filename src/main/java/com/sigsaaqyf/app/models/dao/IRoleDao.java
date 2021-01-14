package com.sigsaaqyf.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Role;

@Repository
public interface IRoleDao extends CrudRepository<Role, Long>{
	public Role findByNombre(String nombre);
}
