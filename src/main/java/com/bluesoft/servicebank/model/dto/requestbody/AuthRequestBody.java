package com.bluesoft.servicebank.model.dto.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestBody implements Serializable {

    private String dni;
    private String password;

}
