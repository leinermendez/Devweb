package ucr.ac.cr.Devweb.model.DTO;

public class ProjectDTO {

    private String title;
    private Long id;
    private String freelancerName;
    private String imageUrl;

    public ProjectDTO() {
    }
    public ProjectDTO(Long id, String title, String freelancerName, String imageUrl) {
        this.id = id;
        this.title = title;
        this.freelancerName = freelancerName;
        this.imageUrl = imageUrl;
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

}
