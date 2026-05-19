package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String passwod;
    private String rol;
    private Boolean verified;
    private Double rating;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(String id, String name, String passwod, String rol, Boolean verified, Double rating, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.passwod = passwod;
        this.rol = rol;
        this.verified = verified;
        this.rating = rating;
        this.createdAt = createdAt;
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

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", passwod='" + passwod + '\'' +
                ", rol='" + rol + '\'' +
                ", verified=" + verified +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                '}';
    }
}
