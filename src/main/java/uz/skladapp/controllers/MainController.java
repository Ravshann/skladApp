package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.skladapp.model.Company;
import uz.skladapp.repositories.CompanyRepository;

import java.util.Optional;

@RestController
public class MainController {


    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping("/companies")
    public @ResponseBody Iterable<Company> getList() {
        return companyRepository.findAll();
    }

    @RequestMapping("/companies/")
    public Optional<Company> getCompany(@RequestParam(value="id", defaultValue="1") String id) {
        return companyRepository.findById(Long.valueOf(id));
    }



}
