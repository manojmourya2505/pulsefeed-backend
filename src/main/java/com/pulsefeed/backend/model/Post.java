package com.pulsefeed.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Image url cannot be black")
    private String imageUrl;

    @NotBlank(message = "Caption cannot be empty")
    @Column(length = 500)
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    public Post(String imageUrl,String caption,User user){
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.user = user;
    }

    public Long getId(){
        return id;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getCaption(){
        return caption;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }


}
