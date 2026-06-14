package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.model.User;

import java.time.LocalDateTime;

public class ReviewDTO {

    private String comment;
    private Integer rating;
    private LocalDateTime date;
    private String clientName;


    public ReviewDTO(String comment, Integer rating, LocalDateTime date, String clientName) {
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.clientName = clientName;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }
}
