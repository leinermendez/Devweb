package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ucr.ac.cr.Devweb.enums.Role;
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
    public ReviewDTO createReview(Review review) {
        User client = userRepository
                .findById(review.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        User freelancer = userRepository
                .findById(review.getFreelancer().getId())
                .orElseThrow(() -> new RuntimeException("Freelancer not found"));

        if (client.getId().equals(freelancer.getId())){//valida si los dos usuarios tienen el mismo id
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You can't write a review on your own account");
        }

        if (freelancer.getRole()== Role.ADMIN){//valida el rol del destinario de la reseña
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"An administrator cannot receive reviews.");
        }

        if (reviewRepository.existsByClientAndFreelancerAndComment(client, freelancer, review.getComment())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Duplicate reviews are not allowed.");
        }
        review.setClient(client);
        review.setFreelancer(freelancer);
        review.setDate(LocalDateTime.now());
        reviewRepository.save(review);


        return converDTO(review);
    }

    //OPTIENE TODAS LAS REVIEWS
    public List<ReviewDTO> findAll() {
        List<Review> results = this.reviewRepository.findAll();
        return converterListDTO(results);
    }

    //OPTENER LAS REVIEWS DEPENDIENDO DEL ID DEL FREELANCER
    public List<ReviewDTO> findReviewFre(Long id) {
        List<Review> results = this.reviewRepository.findByFreelancerId(id);
        return converterListDTO(results);
    }
    //OPTENER LAS REVIEWS DEPENDIENDO DEL ID DEL USUARIO
    public List<ReviewDTO> findReviewUser(Long id) {
        List<Review> results = this.reviewRepository.findByClientId(id);
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
        ReviewDTO reviewDTO= new ReviewDTO(review.getComment(),review.getRating(),review.getDate(),review.getClient().getName(),review.getFreelancer().getName());
        return reviewDTO;
    }

    //Creamos la lista de reviews segun el DTO
    public List<ReviewDTO> converterListDTO(List<Review> listReview) {
        List<ReviewDTO> listDTO = new ArrayList<>();
        for (Review review : listReview) {
            listDTO.add(this.converDTO(review));
        }
        return listDTO;
    }
}
