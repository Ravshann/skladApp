package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.UserService;
import uz.skladapp.model.pure_models.User;
import uz.skladapp.DTO.UserRolePermissions;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService dao;

    @GetMapping("")
    public Iterable<User> getAllUsers() {

        return dao.getUserList();
    }

    //that was just for testing


//    @GetMapping("/create/{id}")
//    public void createZip(@PathVariable("id") String id) {
//        ZipGenerator zipGenerator = new ZipGenerator("Screen Shot 2019-07-17 at 8.53.04 PM.png");
//        zipGenerator.generate();
//    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        return dao.getUser(Long.valueOf(id));
    }

    @GetMapping("/role_permissions/{id}")
    public UserRolePermissions getUserPermissions(@PathVariable("id") String id) {
        return dao.getUserPermissions(Long.valueOf(id));
    }

    @PostMapping("")
    public void create(@RequestBody String data) throws Exception {
        dao.saveUser(data);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    User replace(@RequestBody String user, @PathVariable Long id) throws Exception {
        return dao.update(user, id);
    }


}
