package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.enums.VerificationStatus;
import ucr.ac.cr.Devweb.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Integer> {
        List<Verification> findByStatus(VerificationStatus status);//filtra las verificaciones por el estado



}



