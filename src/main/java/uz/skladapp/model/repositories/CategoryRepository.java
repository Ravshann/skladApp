package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
