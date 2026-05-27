package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.VerificationStatus;

import java.time.LocalDateTime;

public class VerificationDTO {
    private Long id; // necesario para que el admin pueda aprobar/rechazar
    private String evidenceUrl;
    private VerificationStatus status;
    private LocalDateTime requestDate;
    private String userName; // solo el nombre, no todo el objeto User

    public VerificationDTO() {
    }

    public VerificationDTO(Long id, String evidenceUrl, VerificationStatus status,
                           LocalDateTime requestDate, String userName) {
        this.id = id;
        this.evidenceUrl = evidenceUrl;
        this.status = status;
        this.requestDate = requestDate;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvidenceUrl() {
        return evidenceUrl;
    }

    public void setEvidenceUrl(String evidenceUrl) {
        this.evidenceUrl = evidenceUrl;
    }

    public VerificationStatus getStatus() {
        return status;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
