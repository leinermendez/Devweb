package model;

import enums.VerificationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Verification {
    @Id
    private Long id;
    private VerificationStatus status;
    private String evidenceUrl;
    private LocalDateTime requestDate;
    private User user;

    public Verification(){
    }

    public Verification(Long id, VerificationStatus status, String evidenceUrl, LocalDateTime requestDate, User user) {
        this.id = id;
        this.status = status;
        this.evidenceUrl = evidenceUrl;
        this.requestDate = requestDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VerificationStatus getStatus() {
        return status;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    public String getEvidenceUrl() {
        return evidenceUrl;
    }

    public void setEvidenceUrl(String evidenceUrl) {
        this.evidenceUrl = evidenceUrl;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "id=" + id +
                ", status=" + status +
                ", evidenceUrl='" + evidenceUrl + '\'' +
                ", requestDate=" + requestDate +
                ", user=" + user +
                '}';
    }
}
