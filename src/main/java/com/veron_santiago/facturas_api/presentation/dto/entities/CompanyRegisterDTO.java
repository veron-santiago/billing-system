package com.veron_santiago.facturas_api.presentation.dto.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRegisterDTO {
    private String companyName;
    private String password;
    private String email;
}
