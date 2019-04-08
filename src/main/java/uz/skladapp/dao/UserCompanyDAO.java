package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Company;
import uz.skladapp.model.User;
import uz.skladapp.model.UserCompany;
import uz.skladapp.model.repositories.CompanyRepository;
import uz.skladapp.model.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserCompanyDAO {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getList(Long id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        List<Company> companies = new ArrayList<>();
        for (UserCompany userCompany : user.get().getCompanies()) {
            companies.add(userCompany.getCompany());

        }
        return companies;
    }

    public void addCompany(String ids) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(ids);
        Long id_u = Long.valueOf(newJson.get("user_ID").toString());
        Long id_c = Long.valueOf(newJson.get("company_ID").toString());
        Optional<User> user = userRepository.findById(id_u);
        Optional<Company> company = companyRepository.findById(id_c);
        user.get().addCompany(company.get());
        userRepository.save(user.get());
    }

    public void delete(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_u = Long.valueOf(json.get("user_ID").toString());
        Long id_c = Long.valueOf(json.get("company_ID").toString());
        Optional<User> user = userRepository.findById(id_u);
        Optional<Company> company = companyRepository.findById(id_c);
        user.get().removeCompany(company.get());
        userRepository.save(user.get());


    }

}
