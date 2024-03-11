package com.bluesoft.servicebank.security;

import com.bluesoft.servicebank.model.entity.Cliente;
import com.bluesoft.servicebank.repository.IClienteRepository;
import com.bluesoft.servicebank.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {

        Cliente usuario = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + dni + " no se encuentra registrado en el sistema."));

        Set<GrantedAuthority> authorities =
                Stream.of(SecurityUtil.convertToAuthority("ADMIN")).collect(Collectors.toSet());

        return UserDetail.builder()
                .user(usuario)
                .id(usuario.getId())
                .username(usuario.getDni())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();

    }

}
