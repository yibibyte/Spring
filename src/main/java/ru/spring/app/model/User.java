package ru.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Name is mandatory")
//    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
//    private String name;
//
//    @NotBlank(message = "Email is mandatory")
//    @Email(message = "Email should be valid")
//    private String email;
//
//    @NotBlank(message = "Password is mandatory")
//    @Size(min = 6, message = "Password must be at least 6 characters")
//    private String password;
//
//    // Геттеры, сеттеры и конструкторы
//    public User() {}
//
//    public User(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}