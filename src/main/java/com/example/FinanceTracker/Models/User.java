package com.example.FinanceTracker.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @NotEmpty(message ="enter username")
    @Column(unique = true, nullable = false)
    private String userName;
    @NotEmpty(message ="enter password")
    private String password;
    @Column(unique = true, nullable = false)
    @NotEmpty(message ="enter email")
    private String email;

    public User(long id, String userName, String password, String email) {
        this.user_id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public User() {}

    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public  String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public  String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }


}
