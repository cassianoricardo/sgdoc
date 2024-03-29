package br.com.freelancer.sgdoc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import br.com.freelancer.sgdoc.model.Usuario;
import br.com.freelancer.sgdoc.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String pPsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(pPsuario);
		
		if(null == usuario) {
			 throw new UsernameNotFoundException("O Usuario: "+ pPsuario + "não foi encontrado!"); 
		}
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

}
