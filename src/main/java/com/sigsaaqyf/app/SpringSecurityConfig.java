package com.sigsaaqyf.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sigsaaqyf.app.models.services.JpaUserDetailService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JpaUserDetailService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		//PasswordEncoder encoder = passwordEncoder();
		builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/usuario/registro").permitAll()
		.antMatchers("/usuario/**").hasAnyRole("admin")
		.antMatchers("/usuario/lista").hasAnyRole("admin","jefe")
		.antMatchers("/ciclo/**").hasAnyRole("admin")
		.antMatchers("/materia/**").hasAnyRole("admin")
		.antMatchers("/departamento/**").hasAnyRole("admin")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
		
	}
	
	
}
