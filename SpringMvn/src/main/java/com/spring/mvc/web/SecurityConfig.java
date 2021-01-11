package com.spring.mvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	// Configuracion y creacion de usuarios en memoria
//	// AUTENTICACION
//	// El password normalmente ira encriptado pero si no lo queremos encriptado
//	// ponemos "{noop}"
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) {
//		try {
//			auth.inMemoryAuthentication()
//
//					.withUser("admin").password("{noop}123").roles("ADMIN", "USER").and().withUser("user")
//					.password("{noop}123").roles("USER");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) {
		try {
			build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// AUTORIZACION

	// URLs que queremos restringir. En este ejemplo se especifica que el usuario
	// admin tiene acceso a las urls editar, agregar y eliminar y sus derivados
	// Por otro lados los usuarios "User" y "Admin" tendran acceso a la raiz que es
	// donde se encuentra el listado de usuarios
	@Override
	protected void configure(HttpSecurity http) {
		try {
			http.authorizeRequests().antMatchers("/editar/**", "/agregar/**", "/eliminar").hasRole("ADMIN")
					.antMatchers("/").hasAnyRole("USER", "ADMIN").and().formLogin().loginPage("/login").and()
					.exceptionHandling().accessDeniedPage("/errores/403");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
