 package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.Devweb.model.User;

import java.util.List;
import java.util.Optional;

 @Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFreelancerId(Long freelancerId);//Filtra las reviews por el id del Freelancer
    List<Review> findByClientId(Long clientId);//Filtra las reviews por el id del User
    boolean existsByClientAndFreelancerAndComment(User client, User freelancer, String comment);// verifica la duplicacion entre reseñas
}



