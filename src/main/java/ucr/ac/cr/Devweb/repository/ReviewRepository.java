 package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.Devweb.model.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFreelancerId(Long freelancerId);//Filtra las reviews por el id del Freelancer
    List<Review> findByUserId(Long UserId);//Filtra las reviews por el id del User
}



