package com.bluesoft.servicebank.security;

import com.bluesoft.servicebank.model.entity.Cliente;
import com.bluesoft.servicebank.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {

        Cliente usuario = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + dni + " no se encuentra registrado en el sistema."));

        return UserDetail.builder()
                .user(usuario)
                .id(usuario.getId())
                .username(usuario.getDni())
                .password(usuario.getPassword())
                .build();

    }

}
