package ucr.ac.cr.Devweb.model.DTO;

import ucr.ac.cr.Devweb.enums.Role;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private Boolean verified;
    private Double rating;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, Role role, Boolean verified, Double rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.verified = verified;
        this.rating = rating;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
