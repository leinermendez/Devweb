package ucr.ac.cr.Devweb.model;

import jakarta.persistence.*;
import ucr.ac.cr.Devweb.enums.VerificationStatus;

import java.time.LocalDateTime;

@Entity
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VerificationStatus status;

    private String evidenceUrl;

    private LocalDateTime requestDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Verification(){
    }

    @PrePersist
    public void prePersist(){
        this.requestDate=LocalDateTime.now();
        this.status=VerificationStatus.PENDING;
    }

    public Verification(VerificationStatus status, String evidenceUrl, User user) {
        this.status = status;
        this.evidenceUrl = evidenceUrl;
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
}