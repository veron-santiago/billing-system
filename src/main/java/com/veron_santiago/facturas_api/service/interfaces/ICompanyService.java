package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.presentation.dto.entities.CompanyDTO;

import java.util.List;

public interface ICompanyService {

    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompanyById(Long id);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    void deleteCompany(Long id);

}
