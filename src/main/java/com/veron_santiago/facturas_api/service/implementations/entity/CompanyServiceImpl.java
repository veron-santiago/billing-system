package com.veron_santiago.facturas_api.service.implementations.entity;

import com.veron_santiago.facturas_api.persistence.entity.Company;
import com.veron_santiago.facturas_api.persistence.repository.ICompanyRepository;
import com.veron_santiago.facturas_api.presentation.dto.entities.CompanyDTO;
import com.veron_santiago.facturas_api.service.interfaces.ICompanyService;
import com.veron_santiago.facturas_api.util.JwtUtil;
import com.veron_santiago.facturas_api.util.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final JavaMailSender javaMailSender;
    private final CompanyMapper companyMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public CompanyServiceImpl(ICompanyRepository companyRepository, JavaMailSender javaMailSender, CompanyMapper companyMapper, JwtUtil jwtUtil) {
        this.companyRepository = companyRepository;
        this.javaMailSender = javaMailSender;
        this.companyMapper = companyMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.companyDTOToCompany(companyDTO);
        Company savedCompany = companyRepository.save(company);
        String verificationToken = jwtUtil.generateVerificationToken(savedCompany.getEmail());
        sendVerificationEmail(savedCompany.getEmail(), verificationToken);
        return companyMapper.companyToCompanyDTO(savedCompany);
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compañia no encontrada"));
        return companyMapper.companyToCompanyDTO(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::companyToCompanyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compañia no encontrado"));

        existingCompany.setCompanyName(companyDTO.getCompanyName());
        existingCompany.setEmail(companyDTO.getEmail());
        existingCompany.setAlias(companyDTO.getAlias());
        existingCompany.setCuit(companyDTO.getCuit());

        return companyMapper.companyToCompanyDTO(companyRepository.save(existingCompany));
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("Compañia no encontrada con ID: " + id));
        companyRepository.deleteById(id);
    }

    public String getEmailByCompanyName(String companyName){
        Optional<String> email = companyRepository.findEmailByCompanyName(companyName);
        return email.orElseThrow( () -> new RuntimeException("Compañia no encontrada"));
    }

    public void sendVerificationEmail(String email, String verificationToken){
        String verificationUrl = "http://localhost:8080/verify-email?token=" + verificationToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verificación de Correo Electrónico");
        message.setText("Por favor, haz clic en el siguiente enlace para verificar tu correo electrónico: " + verificationUrl);
        javaMailSender.send(message);
    }

    public boolean verifyEmail(String token){
        String email = jwtUtil.extractEmailFromToken(token);
        if (email == null) {
            return false;
        }
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isEmpty()) {
            return false;
        }
        Company company = optionalCompany.get();
        company.setVerified(true);
        companyRepository.save(company);
        return true;
    }
}
