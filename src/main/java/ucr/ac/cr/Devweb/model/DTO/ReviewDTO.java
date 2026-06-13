package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.model.User;

import java.time.LocalDateTime;

public class ReviewDTO {

    private String comment;
    private Integer rating;
    private LocalDateTime date;
    private User client;

    public ReviewDTO(){

    }

    public ReviewDTO(String comment, Integer rating, LocalDateTime date, User client) {
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.client = client;
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

    public String getClient() {
        return client.getName();
    }

    public void setClient(User client) {
        this.client = client;
    }
}
