package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.model.User;

import java.time.LocalDateTime;

public class RewiewDTO {

    private String comment;
    private Integer rating;
    private LocalDateTime date;
    private User client;
    private User freelancer;

    public RewiewDTO() {
    }

    public RewiewDTO(String comment, Integer rating, User client, User freelancer) {
        this.comment = comment;
        this.rating = rating;
        this.client = client;
        this.freelancer = freelancer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }
}
