package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.Category;

public class ServicesDTO {

    private String title;
    private Long id;
    private Category category;
    private String freelancerName;
    private Double price;

    public ServicesDTO() {
    }

    public ServicesDTO(String title, Long id, Category category, String freelancerName, Double price) {
        this.title = title;
        this.id = id;
        this.category = category;
        this.freelancerName = freelancerName;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
