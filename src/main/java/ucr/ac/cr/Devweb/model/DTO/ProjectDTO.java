package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.Category;

public class ProjectDTO {

    private String title;
    private Long id;
    private String freelancerName;
    private String imageUrl;
    private String description;
    private String category;
    private Long freelancerId;

    public ProjectDTO() {
    }
    public ProjectDTO(Long id, String title, String description, String category, String freelancerName, String imageUrl, Long freelancerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.freelancerName = freelancerName;
        this.imageUrl = imageUrl;
        this.freelancerId = freelancerId;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }
}
