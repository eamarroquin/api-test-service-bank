package com.bluesoft.servicebank.service.auth;

import com.bluesoft.servicebank.exception.BadRequestException;
import com.bluesoft.servicebank.model.dto.ClienteDTO;
import com.bluesoft.servicebank.model.dto.requestbody.AuthRequestBody;
import com.bluesoft.servicebank.model.entity.Cliente;
import com.bluesoft.servicebank.repository.IClienteRepository;
import com.bluesoft.servicebank.security.UserDetail;
import com.bluesoft.servicebank.security.jwt.JwtProvider;
import com.bluesoft.servicebank.service.mapper.IClienteMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final IClienteRepository clienteRepository;
    private final IClienteMapper clienteMapper;

    @Override
    public ClienteDTO iniciarSesion(AuthRequestBody authDTO) {

        Cliente usuario = clienteRepository.findByDni(authDTO.getDni())
                .orElseThrow(() -> new UsernameNotFoundException("El cliente no se encuentra registrado en el sistema."));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getDni(), authDTO.getPassword())
        );

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(userDetail);

        ClienteDTO clienteDTO = clienteMapper.entityToDto(usuario);
        clienteDTO.setAuthToken(jwt);

        return clienteDTO;

    }

    @Override
    public ClienteDTO registrarCliente(ClienteDTO dto) {

        validarInformacion(dto);

        String password = dto.getPassword();

        dto.setDni(dto.getDni().replaceAll("\\s+", ""));
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        ClienteDTO clienteDTO = clienteMapper
                .entityToDto(clienteRepository.save(clienteMapper.dtoToEntity(dto)));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getDni(), password)
        );

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(userDetail);

        clienteDTO.setAuthToken(jwt);

        return clienteDTO;
    }

    private void validarInformacion(ClienteDTO dto) {

        if (Objects.isNull(dto.getDni()) || Strings.isBlank(dto.getDni().trim())) {
            throw new BadRequestException("El DNI es obligatorio.");
        }
        if (Objects.isNull(dto.getNombre()) || Strings.isBlank(dto.getNombre().trim())) {
            throw new BadRequestException("El nombre es obligatorio.");
        }
        if (Objects.isNull(dto.getPassword()) || Strings.isBlank(dto.getPassword().trim())) {
            throw new BadRequestException("La contraseña es requerida.");
        }

        boolean isPresent = clienteRepository.existsByDni(dto.getDni().replaceAll("\\s+", ""));

        if (isPresent) {
            throw new BadRequestException("El cliente ya se encuentra registrado en el sistema.");
        }

    }

}
