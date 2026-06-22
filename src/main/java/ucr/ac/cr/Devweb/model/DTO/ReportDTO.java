package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportType;

import java.time.LocalDateTime;

public class ReportDTO {
    private Long id;
    private ReportType type;
    private String description;
    private UserDTO reportedBy;
    private ReportStatus status;
    private LocalDateTime createdAt;
    private Long reportedProjectId;
    private UserDTO reportedUser;

    public ReportDTO(Long id, ReportType type, String description, UserDTO reportedBy, ReportStatus status, LocalDateTime createdAt, Long reportedProjectId, UserDTO reportedUser) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.reportedBy = reportedBy;
        this.status = status;
        this.createdAt = createdAt;
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

    public UserDTO getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(UserDTO reportedBy) {
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

    public UserDTO getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(UserDTO reportedUser) {
        this.reportedUser = reportedUser;
    }
}