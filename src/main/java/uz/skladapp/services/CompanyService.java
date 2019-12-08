package uz.skladapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Company;
import uz.skladapp.repositories.CompanyRepository;
import uz.skladapp.DTO.CompanyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;

    public Iterable<CompanyDTO> getAll() {
        List<Company> originals = repository.findAll();
        List<CompanyDTO> raws = new ArrayList<>();
        for (Company object : originals) {
            CompanyDTO raw = new CompanyDTO(object.getCompany_ID(), object.getName(), object.getAddress(), object.getCompany_phone());
            raws.add(raw);
        }
        return raws;
    }

    public CompanyDTO get(Long id) {

        Optional<Company> object = repository.findById(id);
        if (object.isPresent())
            return new CompanyDTO(
                    object.get().getCompany_ID(),
                    object.get().getName(),
                    object.get().getAddress(),
                    object.get().getCompany_phone());
        else
            return new CompanyDTO();
    }

    public void create(CompanyDTO dto) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(string);


        //extracting data json
//        object.setAddress(json.get("address").asText());
//        object.setCompany_phone(json.get("company_phone").asText());
//        object.setName(json.get("name").asText());
        Company object = new Company();
        object.setAddress(dto.getAddress());
        object.setCompany_phone(dto.getCompany_phone());
        object.setName(dto.getName());

        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Company update(CompanyDTO dto, Long id) {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(object -> {
//                    object.setAddress(json.get("address").asText());
//                    object.setCompany_phone(json.get("company_phone").asText());
//                    object.setName(json.get("name").asText());
                    object.setAddress(dto.getAddress());
                    object.setName(dto.getName());
                    object.setCompany_phone(dto.getCompany_phone());
                    return repository.save(object);
                })
                .get();
    }
}
