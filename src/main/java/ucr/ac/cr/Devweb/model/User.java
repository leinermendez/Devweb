package ucr.ac.cr.Devweb.model;

import ucr.ac.cr.Devweb.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    //generar el Id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private Boolean verified;
    private Double rating;


    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;


    public User() {
    }


    //Prepersist le dice a Spring que antes de guardar este objeto en la base de datos, ejecute este código
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


    //Contructor, no incluye el Id ni la fecha de creacion porque eso se hara automaticamente y no es un dato que se deba introducir al crearlo
    public User(String name, String password, String email, Role role, Boolean verified, Double rating) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre ='" + name + '\'' +
                ", contrasenia='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + role + '\'' +
                ", verificado=" + verified +
                ", calificacion=" + rating +
                ", Fecha de creación=" + createdAt +
                '}';
    }
}
