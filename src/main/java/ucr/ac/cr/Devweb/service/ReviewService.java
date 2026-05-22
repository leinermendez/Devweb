package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.model.Review;
import ucr.ac.cr.Devweb.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //CREAR UNA NUEVA REVIEW OPTENIENDO LOS DATOS DEL FRONTEND
    public Review createReview(Review review) {
        review.setDate(LocalDateTime.now());
        return this.reviewRepository.save(review);
    }

    //OPTENER LAS REVIEWS DEPENDIENDO DEL ID DEL FREELANCER
    public List<Review> findReviewFre(Long id){
        List<Review> resultd= this.reviewRepository.findByFreelancerID(id);
        return resultd;
    }

    //ELIMINACION DE UNA REVIEW POR MEDIO DE SU ID
    public void deleteReview(Long id){
        this.deleteReview(id);
    }
}
