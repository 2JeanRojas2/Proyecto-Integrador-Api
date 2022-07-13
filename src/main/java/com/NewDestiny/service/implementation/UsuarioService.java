package com.NewDestiny.service.implementation;

import com.NewDestiny.model.entity.UserEntity;
import com.NewDestiny.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByEmail(username);

        if (user == null) {
            String msj = "usuario no existe ".concat(username);
            log.error(msj);
            throw new UsernameNotFoundException(msj);
        }
        /**
         * Debemos sacar el nombre del ROL para que pueda funcionar de manera correcta, ya que el usuario
         * de userDetails requiere las autoridades para acceder.
         * */
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(authority);
        log.info(user.getRole().getName());
        /** le envio el password sacado de base de datos para que User lo compare con el password que viene
         * en en el formulario*/
        return new User(user.getEmail(), user.getPassword(), user.isEnabled()
                , true, true, true, authorities);
    }
}
