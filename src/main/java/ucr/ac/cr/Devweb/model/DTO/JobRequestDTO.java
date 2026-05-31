package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.RequestStatus;

public class JobRequestDTO {

    private Long id;
    private String description;
    private RequestStatus status;
    private Long clientId;
    private Long freelancerId;
    private Long serviceId;

    public JobRequestDTO() {

    }

    public JobRequestDTO(Long id, String description, RequestStatus status, Long clientId, Long freelancerId, Long serviceId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
        this.serviceId = serviceId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}

