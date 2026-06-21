package ucr.ac.cr.Devweb.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ucr.ac.cr.Devweb.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    //generar el Id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio") //verifica que el campo no esté vacío ni sea solo espacios.
    private String name;


    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

    @Email(message = "Email inválido") // verifica que el texto tenga formato de email válido.
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true, nullable = false)
    private String email;

    private Boolean verified;
    private Double rating;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public User() {
    }

    //Prepersist le dice a Spring que antes de guardar este objeto en la base de datos, ejecute este código
    @PrePersist
    public void prePersist() {
        this.verified = false;
        this.rating = 0.0;
        this.createdAt = LocalDateTime.now();
    }



    //Contructor, no incluye el Id ni fecha de creacion porque eso se hara automaticamente
    // Tampoco recibe rating porque al crearse un usuario no deberia tener una calificacion y de mismo caso no deberia estar verificado
    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
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
