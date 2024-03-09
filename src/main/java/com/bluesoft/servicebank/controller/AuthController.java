package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.ClienteDTO;
import com.bluesoft.servicebank.model.dto.requestbody.AuthRequestBody;
import com.bluesoft.servicebank.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ClienteDTO> signIn(@RequestBody AuthRequestBody authDTO) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.iniciarSesion(authDTO));

    }

    @PostMapping("/sign-up")
    public ResponseEntity<ClienteDTO> signUp(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.registrarCliente(clienteDTO));
    }

}
