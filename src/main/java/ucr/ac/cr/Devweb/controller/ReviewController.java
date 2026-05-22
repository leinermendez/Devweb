package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.model.DTO.ReviewDTO;
import ucr.ac.cr.Devweb.model.Review;
import ucr.ac.cr.Devweb.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findReviewByFreelancerID(@PathVariable Long id){
        List<ReviewDTO> reviews= this.reviewService.findReviewFre(id);
        if(reviews.isEmpty()) {
            return ResponseEntity.ok("Freelancer " + id + " has no reviews");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Average", this.reviewService.average(id));
        response.put("Reviews",reviews);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> saveReview(@Validated @RequestBody Review review, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Review newReview = this.reviewService.createReview(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        this.reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
