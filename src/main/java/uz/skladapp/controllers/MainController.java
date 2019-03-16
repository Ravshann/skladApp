package uz.skladapp.controllers;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.model.Category;
import uz.skladapp.model.Company;
import uz.skladapp.repositories.CategoryRepository;
import uz.skladapp.repositories.CompanyRepository;


import java.security.Key;
import java.util.Optional;

@RestController
public class MainController {


    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping("/companies")
    public @ResponseBody
    Iterable<Company> getList() {
        return companyRepository.findAll();
    }

    @RequestMapping("/companies/")
    public Optional<Company> getCompany(@RequestParam(value = "id", defaultValue = "1") String id) {


        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder()

                .setSubject("{\'username\':\'root\',\'password\':\'root\'}")

                .signWith(key)

                .compact();

        System.out.println("this is " + jws);

        try {
            Jws<Claims> a=Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
            String text = a.getBody().getSubject();
            System.out.println("hti is fine\n"+text);
            //OK, we can trust this JWT

        } catch (JwtException e) {

            System.out.println(e.getMessage());
            //don't trust the JWT!
        }
        return companyRepository.findById(Long.valueOf(id));
    }

    @PostMapping("/company")
    public void saveCompany(@RequestBody Company company) {
        companyRepository.save(company);
    }


    //migo

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public @ResponseBody
    Page<Category> getList(@RequestParam(value = "page") String page) {
        return categoryRepository.findAll(PageRequest.of(Integer.valueOf(page), 2));
    }

    @RequestMapping("/categories/")
    public Optional<Category> getCategory(@RequestParam(value = "id", defaultValue = "0") String id) {
        return categoryRepository.findById(Long.valueOf(id));
    }
    //end migo

}
