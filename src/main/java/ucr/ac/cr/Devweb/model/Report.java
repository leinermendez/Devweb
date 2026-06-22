package ucr.ac.cr.Devweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportType;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    //generar el Id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "The type of report is mandatory")
    @Enumerated(EnumType.STRING)
    private ReportType type;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_to_report",nullable = false)
    private User reportedBy;


    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "reported_project_id")
    private Long reportedProjectId;

    @ManyToOne
    @JoinColumn(name = "reported_user")
    private User reportedUser;

    public Report() {
    }

    @PrePersist//Fecha automatica
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status= ReportStatus.PENDING;
    }

    public Report(ReportType type, String description, User reportedBy, Long reportedProjectId, User reportedUser) {
        this.type = type;
        this.description = description;
        this.reportedBy = reportedBy;
        this.reportedProjectId = reportedProjectId;
        this.reportedUser = reportedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getReportedProjectId() {
        return reportedProjectId;
    }

    public void setReportedProjectId(Long reportedProjectId) {
        this.reportedProjectId = reportedProjectId;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }
}
