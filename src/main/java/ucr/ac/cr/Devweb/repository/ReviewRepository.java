package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

