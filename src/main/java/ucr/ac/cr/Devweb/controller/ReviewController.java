package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        this.reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
