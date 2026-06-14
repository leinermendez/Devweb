package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.enums.VerificationStatus;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
        List<Verification> findByStatus(VerificationStatus status);//filtra las verificaciones por el estado
        Optional<Verification> findById(Long id);
        boolean existsByUserAndStatus(User user, VerificationStatus status);

}



