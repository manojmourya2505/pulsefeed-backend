package com.pulsefeed.backend.dto;

public class LoginRequest {
    private String email, password;

    //Setter's and Getter's
    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public  String  getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
