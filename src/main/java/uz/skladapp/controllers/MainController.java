package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.skladapp.model.*;
import uz.skladapp.model.repositories.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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


    @PostMapping("/company")
    public void saveCompany(@RequestBody Company company) {
        companyRepository.save(company);
    }


    //migo

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public @ResponseBody
    Page<Category> getCategoryList(@RequestParam(value = "page") String page) {
        return categoryRepository.findAll(PageRequest.of(Integer.valueOf(page), 2));
    }

    //@PathVariable("id") String id --> {id} --> works without __link__ and other staff ))
    @RequestMapping("/categories/{id}")
    public Optional<Category> getCategory(@PathVariable("id") String id) {
        return categoryRepository.findById(Long.valueOf(id));
    }
    //end migo

    //migo Role

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/roles")
    public @ResponseBody
    Iterable<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @RequestMapping("/roles/{id}")
    public Optional<Role> getRole(@PathVariable("id") String id) {
        return roleRepository.findById(Long.valueOf(id));
    }
    //migo User

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public @ResponseBody
    Iterable<User> getUserList() {
        return userRepository.findAll();
    }

    @RequestMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable("id") String id) {
        return userRepository.findById(Long.valueOf(id));
    }

    //migo Product

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/products")
    public @ResponseBody
    Iterable<Product> getProductList() {
        return productRepository.findAll();
    }

    @RequestMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable("id") String id) {
        return productRepository.findById(Long.valueOf(id));
    }

    //migo Attribute

    @Autowired
    private AttributeRepository attributeRepository;

    @RequestMapping("/attributes")
    public @ResponseBody
    Iterable<Attribute> getAttributeList() {
        return attributeRepository.findAll();
    }

    @RequestMapping("/attributes/{id}")
    public Optional<Attribute> getAttributes(@PathVariable("id") String id) {
        return attributeRepository.findById(Long.valueOf(id));
    }

    //migo Attribute

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/departments")
    public @ResponseBody
    Iterable<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }

    @RequestMapping("/departments/{id}")
    public Optional<Department> getDepartment(@PathVariable("id") String id) {
        return departmentRepository.findById(Long.valueOf(id));
    }

    //migo Storage

    @Autowired
    private StorageRepository storageRepository;

    @RequestMapping("/storages")
    public @ResponseBody
    Iterable<Storage> getStorageList() {
        return storageRepository.findAll();
    }

    @RequestMapping("/storages/{id}")
    public Optional<Storage> getStorage(@PathVariable("id") String id) {
        return storageRepository.findById(Long.valueOf(id));
    }

    //migo StorageProduct

//    @Autowired
//    private Storage_ProductRepository storage_productRepository;

//    @RequestMapping("/storage_product")
//    public @ResponseBody
//    Iterable<StorageProduct> getStorageProductList() {
//        return storage_productRepository.findAll();
//    }

//    @RequestMapping("/storage_product/")
//    public Optional<StorageProduct> getStorageProduct(@RequestParam(value = "id", defaultValue = "0") String id) {
//        return storageRepository.findById(Long.valueOf(id));
//    }

    //migo Storage

    @Autowired
    private PermissionRepository permissionRepository;

    @RequestMapping("/permission")
    public @ResponseBody
    Iterable<Permission> getPermissionList() {
        return permissionRepository.findAll();
    }

    @RequestMapping("/permission/{id}")
    public Optional<Permission> getPermission(@PathVariable("id") String id) {
        return permissionRepository.findById(Long.valueOf(id));
    }


    //rav
    @RequestMapping("/product_attribute/{pr}")
    public List<Attribute> getList(@PathVariable("pr") String id) {
        Optional<Product> pro = productRepository.findById(Long.valueOf(id));
        List<Attribute> atrs = new ArrayList<>();
        for (ProductAttribute attr : pro.get().getAttributes()) {
            atrs.add(attr.getAttribute());

        }
        return atrs;
    }

    @RequestMapping("/storage_products/{store}")
    public List<Long> getListProducts(@PathVariable("store") String id) {
        Optional<Storage> s = storageRepository.findById(Long.valueOf(id));
        List<Long> atrs = new ArrayList<>();
        for (StorageProduct pro : s.get().getProducts()) {
            atrs.add(pro.getProduct().getProduct_ID());

        }
        return atrs;
    }

    @GetMapping("/user-excel")
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView(new ExcelView());

    }

}
