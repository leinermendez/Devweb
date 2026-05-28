/*package ucr.ac.cr.Devweb.model;

import ucr.ac.cr.Devweb.enums.RequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class JobRequest {
    @Id
    private Long id;
    private String description;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private User client;
    private User freelancer;
    private Services services;

    public JobRequest(){

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
                ", client=" + client +
                ", freelancer=" + freelancer +
                ", service=" + services +
                '}';
    }
}


 */