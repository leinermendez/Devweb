package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    private Long id;
    private String comment;
    private Integer rating;
    private LocalDateTime date;
    private User client;
    private User freelancer;

    public Review() {
    }

    public Review(Long id, String comment, Integer rating, LocalDateTime date, User client, User freelancer) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.client = client;
        this.freelancer = freelancer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", client=" + client +
                ", freelancer=" + freelancer +
                '}';
    }
}
