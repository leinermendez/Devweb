package ucr.ac.cr.Devweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ucr.ac.cr.Devweb.enums.RequestStatus;

import java.time.LocalDateTime;

@Entity
public class JobRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @NotNull(message = "El id del cliente es obligatorio")//no puede quedar null
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)// nullable = false es una validacion para que no quedé como null
    private User client;

    @NotNull(message = "El id del freelancer es obligatorio")
    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;

    @NotNull(message = "El servicio es obligatorio")
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services services;

    public JobRequest(){

    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        //pone el status inicial de las peticiones en pendiente
        if(this.status == null){
            this.status = RequestStatus.PENDING;
        }
    }

    public JobRequest(Long id, String description, RequestStatus status, LocalDateTime createdAt, User client, User freelancer, Services services) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.client = client;
        this.freelancer = freelancer;
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public Services getService() {
        return services;
    }

    public void setService(Services services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "JobRequest{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}


