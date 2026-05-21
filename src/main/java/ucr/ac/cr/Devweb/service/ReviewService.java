package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.model.Review;
import ucr.ac.cr.Devweb.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findReviewFre(Long id){
        List<Review> resultd= this.reviewRepository.findByFreelancerID(id);
        return resultd;
    }

    public void deleteReview(Long id){
        this.deleteReview(id);
    }
}
