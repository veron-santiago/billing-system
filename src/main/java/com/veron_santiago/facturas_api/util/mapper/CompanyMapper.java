package com.veron_santiago.facturas_api.util.mapper;

import com.veron_santiago.facturas_api.persistence.entity.Company;
import com.veron_santiago.facturas_api.presentation.dto.entities.CompanyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO companyToCompanyDTO(Company company);
    Company companyDTOToCompany(CompanyDTO companyDTO);
}
