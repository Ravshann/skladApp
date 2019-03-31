package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.skladapp.model.Department;
import uz.skladapp.model.Role;
import uz.skladapp.model.Storage;
import uz.skladapp.model.User;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.repositories.RoleRepository;
import uz.skladapp.model.repositories.StorageRepository;
import uz.skladapp.model.repositories.UserRepository;

import java.util.Optional;

@Component
public class StorageDAO {
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;



    public Iterable<Storage> getStorageList() {
        return storageRepository.findAll();
    }

    public Optional<Storage> getStorageByID(Long id){
        Optional<Storage> storage= storageRepository.findById(id);
        return storage;
    }

    public void saveStorage(String newText) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(newText);
        Long d_id = Long.valueOf(jsonNode.get("department_ID").toString());
        Optional<Department> department = departmentRepository.findById(d_id);
        Long u_id = Long.valueOf(jsonNode.get("storage_manager_ID").toString());
        Optional<User> user= userRepository.findById(u_id);

        Storage newStorage = new Storage();

        newStorage.setAddress(jsonNode.get("address").asText());
        newStorage.setDepartment_ID(department.get());
        newStorage.setStorage_manager_ID(user.get());
        newStorage.setStorage_name(jsonNode.get("storage_name").asText());
        newStorage.setStorage_phone(jsonNode.get("storage_phone").asText());
        storageRepository.save(newStorage);
    }
}
