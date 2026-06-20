package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportTargetType;

import java.time.LocalDateTime;

public class ReportDTO {
    private Long id;
    private String description;
    private UserDTO client;
    private ReportStatus status;
    private LocalDateTime date;
    private Long contentId;
    private ReportTargetType targetType;

    public ReportDTO(Long id, String description, UserDTO client, ReportStatus status, LocalDateTime date, Long contentId, ReportTargetType targetType) {
        this.id = id;
        this.description = description;
        this.client = client;
        this.status = status;
        this.date = date;
        this.contentId = contentId;
        this.targetType = targetType;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getClient() {
        return client;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getContentId() {
        return contentId;
    }

    public ReportTargetType getTargetType() {
        return targetType;
    }
}
