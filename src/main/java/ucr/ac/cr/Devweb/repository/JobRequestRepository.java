package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {
    List<JobRequest> findAllByClientId(Long clientId);

    List<JobRequest> findAllByFreelancerId(Long freelancerId);
}




