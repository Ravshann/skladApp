package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
