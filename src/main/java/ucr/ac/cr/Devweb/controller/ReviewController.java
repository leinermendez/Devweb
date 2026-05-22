package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucr.ac.cr.Devweb.model.Review;
import ucr.ac.cr.Devweb.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findReviewByFreelancerID(@PathVariable Long id){
        List<Review> reviews= this.reviewService.findReviewFre(id);

        if(reviews.isEmpty()) {
            return ResponseEntity.ok("Freelancer " + id + " has no reviews");
        }
        return ResponseEntity.ok(reviews);
    }
}
