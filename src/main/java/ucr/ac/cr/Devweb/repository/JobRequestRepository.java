package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.enums.RequestStatus;
import ucr.ac.cr.Devweb.model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {
    List<JobRequest> findAllByClientId(Long clientId);

    List<JobRequest> findAllByFreelancerId(Long freelancerId);


    // paravalidar si existe el cliente, el freelancer, el servicio y el status que debería tener como base
    boolean existsByClientIdAndFreelancerIdAndServicesIdAndStatus(Long clientId, Long freelancerId, Long servicesId, RequestStatus status);
}




