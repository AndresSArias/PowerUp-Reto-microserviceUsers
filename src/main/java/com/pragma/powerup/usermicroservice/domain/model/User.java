package com.pragma.powerup.usermicroservice.domain.model;
import java.util.Date;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String number_document;
    private String phone;
    private Date date_birth;
    private String email;
    private String password;
    private Role role;

    public User(Long id, String name, String lastName, String number_document, String phone, Date date_birth, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.number_document = number_document;
        this.phone = phone;
        this.date_birth = date_birth;
        this.email = email;
        this.password = password;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber_document() {
        return number_document;
    }

    public void setNumber_document(String number_document) {
        this.number_document = number_document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
