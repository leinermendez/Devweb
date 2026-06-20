package ucr.ac.cr.Devweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportTargetType;
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
    private User client;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(name = "created_at")
    private LocalDateTime date;

    @Column(name = "contend_id",nullable = false)
    private Long contentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReportTargetType targetType;

    public Report() {
    }

    @PrePersist//Fecha automatica
    public void prePersist() {
        this.date = LocalDateTime.now();
        this.status= ReportStatus.PENDING;
    }

    public Report(ReportType type, String description, User client, Long contentId, ReportTargetType targetType) {
        this.type = type;
        this.description = description;
        this.client = client;
        this.contentId = contentId;
        this.targetType = targetType;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public ReportTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(ReportTargetType targetType) {
        this.targetType = targetType;
    }
}
