package model.DTO;

public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String rol;
    private Boolean verified;
    private Double rating;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String email, String rol, Boolean verified, Double rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rol = rol;
        this.verified = verified;
        this.rating = rating;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
