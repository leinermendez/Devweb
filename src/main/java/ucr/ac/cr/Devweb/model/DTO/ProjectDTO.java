package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.model.User;

public class ProjectDTO {

    private String title;
    private Long id;
    private String freelancerName;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String title, String freelancerName) {
        this.id = id;
        this.title = title;
        this.freelancerName = freelancerName;
    }

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
