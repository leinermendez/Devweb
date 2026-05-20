package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Integer> {
}
