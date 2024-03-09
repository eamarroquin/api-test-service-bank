package com.bluesoft.servicebank.service.auth;

import com.bluesoft.servicebank.model.dto.ClienteDTO;
import com.bluesoft.servicebank.model.dto.requestbody.AuthRequestBody;

public interface IAuthService {
    ClienteDTO iniciarSesion(AuthRequestBody authDTO);

    ClienteDTO registrarCliente(ClienteDTO clienteDTO);

}
