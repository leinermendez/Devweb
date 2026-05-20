package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, Integer> {
}
