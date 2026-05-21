package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //busca un usuario por su email
    boolean existsByEmail(String email); //verifica si ya existe un usuario con ese email

}
