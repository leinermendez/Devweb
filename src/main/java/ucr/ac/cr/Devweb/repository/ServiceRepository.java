package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}

