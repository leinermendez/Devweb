package ucr.ac.cr.Devweb.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private LocalDateTime date;

    @ManyToOne //muchos proyectos pueden pertenecer a un solo usuario
    @JoinColumn(name = "freelancer_id")  // en la tabla projects creá una columna llamada freelancer_id que guarde el ID del usuario
    private User freelancer;

    public Project() {
    }

    //Prepersist le dice a Spring que antes de guardar este objeto en la base de datos, ejecute este código
    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }

    public Project(String title, String description, String category, String imageUrl, LocalDateTime date, User freelacer) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.freelancer = freelacer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
