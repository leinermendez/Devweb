package ucr.ac.cr.Devweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Project {

    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private LocalDateTime date;
    private User freelancer;

    public Project() {
    }

    public Project(String id, String title, String description, String category, String imageUrl, LocalDateTime date, User freelacer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.date = date;
        this.freelancer = freelacer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getFreelacer() {
        return freelancer;
    }

    public void setFreelacer(User freelacer) {
        this.freelancer = freelacer;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", freelacer=" + freelancer +
                '}';
    }
}
