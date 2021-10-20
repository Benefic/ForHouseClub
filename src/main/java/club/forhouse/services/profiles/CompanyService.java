package club.forhouse.services.profiles;

import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.CompanyMapper;
import club.forhouse.repositories.profiles.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    public final CompanyRepository companyRepository;
    public final CompanyMapper companyMapper;
    public final UserService userService;


    public List<CompanyDto> findAll() {
        return companyMapper.toListDto(companyRepository.findAll());
    }

    public CompanyDto findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company with id: " + id));
        return companyMapper.toDto(company);
    }

    public CompanyDto saveCompanyFromName(String companyName, String generalManager) {
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setGeneralManager(userService.findByUserName(generalManager));
        companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    public Company findByName(String companyName) {
        Company company = companyRepository.findByCompanyName(companyName).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company with name: " + companyName));
        return company;
    }
}