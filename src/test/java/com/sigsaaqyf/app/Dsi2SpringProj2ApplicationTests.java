package com.sigsaaqyf.app;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sigsaaqyf.app.models.dao.IUsuarioDao;
import com.sigsaaqyf.app.models.entity.Usuario;

@SpringBootTest
class Dsi2SpringProj2ApplicationTests {

	@Autowired
	IUsuarioDao usuarioService;
	
	@Test
	void contextLoads() {
		Usuario u = new Usuario();
		u.setpNombre("desdeJunit");
		u.setpApellido("AO");
		u.setsApellido("segdfg");
		u.setPassword("123456");
		
		
		assertTrue(usuarioService.save(u).getpNombre().equals(u.getpNombre()));
	}

}
