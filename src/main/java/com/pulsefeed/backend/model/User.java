package com.pulsefeed.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid Email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();

    public User(String username,String email, List<Post> post){
        this.username = username;
        this.email = email;
        this.post = post;
    }

    public Long getId(){
        return id;
    }

    public String getUserName(){
        return username;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public List<Post> getPost(){
        return post;
    }

    public void setPost(List<Post> post){
        this.post = post;
    }
}
