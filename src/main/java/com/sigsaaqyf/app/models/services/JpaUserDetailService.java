package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigsaaqyf.app.models.dao.IUsuarioDao;
import com.sigsaaqyf.app.models.entity.Usuario;

@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {
	
	@Autowired
	IUsuarioDao usuarioDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario u = usuarioDao.findByUsername(username).orElse(null);			// 1 obtener el usuario de la base de datos por su "username"
		
		System.out.println(u.getRole().getNombre());	//
		System.out.println(u.getUsername());			// ############ BORRAR!!!!!
		System.out.println(u.getPassword());			//
				
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	// 2 definir la lista de roles del usuario
		authorities.add(new SimpleGrantedAuthority(u.getRole().getNombre()));	// 3 añadimos a la lista de roles el string del rol del usuario
		
		return new User(u.getUsername(), u.getPassword(), true, true, true, true, authorities);
	}

}
