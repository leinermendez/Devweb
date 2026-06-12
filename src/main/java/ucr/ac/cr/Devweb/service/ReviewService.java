package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.model.DTO.ReviewDTO;
import ucr.ac.cr.Devweb.model.Review;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.repository.ReviewRepository;
import ucr.ac.cr.Devweb.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    //CREAR UNA NUEVA REVIEW OPTENIENDO LOS DATOS DEL FRONTEND
    public Review createReview(Review review) {
        User client = userRepository
                .findById(review.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client no encontrado"));
        User freelancer = userRepository
                .findById(review.getFreelancer().getId())
                .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));
        review.setClient(client);
        review.setFreelancer(freelancer);
        review.setDate(LocalDateTime.now());

        return this.reviewRepository.save(review);
    }

    //OPTENER LAS REVIEWS DEPENDIENDO DEL ID DEL FREELANCER
    public List<ReviewDTO> findReviewFre(Long id) {
        List<Review> results = this.reviewRepository.findByFreelancerId(id);
        return converterListDTO(results);
    }

    //OPTENEMOS EL PROMEDIO DEL RATING
    public Double average(Long freelancerId){
        return reviewRepository.findByFreelancerId(freelancerId).stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

    //ELIMINACION DE UNA REVIEW POR MEDIO DE SU ID
    public void deleteReview(Long id){
        this.reviewRepository.deleteById(id);
    }

    //CONVERTIR EN DTO
    public ReviewDTO converDTO(Review review){
        ReviewDTO reviewDTO= new ReviewDTO(review.getComment(),review.getRating(),review.getDate(),review.getClient());
        return reviewDTO;
    }

    public List<ReviewDTO> converterListDTO(List<Review> listReview) {
        List<ReviewDTO> listDTO = new ArrayList<>();
        for (Review review : listReview) {
            listDTO.add(this.converDTO(review));
        }
        return listDTO;
    }
}
