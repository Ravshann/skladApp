package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
