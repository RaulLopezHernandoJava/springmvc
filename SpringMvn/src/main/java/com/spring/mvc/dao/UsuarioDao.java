package com.spring.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.modelos.*;

public interface UsuarioDao extends JpaRepository<Usuario,Long> {
	Usuario findByUsername(String username);
}
