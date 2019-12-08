package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.DepartmentService;
import uz.skladapp.model.pure_models.Department;
import uz.skladapp.DTO.DepartmentDTO;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody Iterable<DepartmentDTO> getList() {
        return dao.getAllDepartments();
    }


//    @GetMapping(value = "/{id}", produces = "application/json")
//    public @ResponseBody DepartmentRaw get(@PathVariable("id") String id) {
//        return dao.getDepartmentByID(Long.valueOf(id));
//    }

    @PostMapping(value = "")
    public void save(@RequestBody String department) throws Exception {
        dao.createDepartment(department);
    }


    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Department replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }


//    ******
//    ============================
//    this version sends broad information
//    ==============================
//    @RequestMapping("/departments/{id}")
//    public DepartmentPure getDepartment(@PathVariable("id") String id) {
//        return DepartmentDAO.getDepartmentByID(Long.valueOf(id));
//    }

}
