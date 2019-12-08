package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
