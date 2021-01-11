package com.spring.mvc.servicio;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.dao.UsuarioDao;
import com.spring.mvc.modelos.*;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}

		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}

		// Este objeto "User" no es creado por nosotros sino que viene de la api de
		// Spring Security
		// Es objeto predefinido

		return new User(usuario.getUsername(), usuario.getPassword(), roles);

	}

}
