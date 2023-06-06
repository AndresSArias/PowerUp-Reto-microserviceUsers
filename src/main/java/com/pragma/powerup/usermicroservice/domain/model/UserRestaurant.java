package com.pragma.powerup.usermicroservice.domain.model;

public class UserRestaurant {

    private Long id;
    private User user;
    private String nitRestaurant;
    private String position;
    private String active;

    public UserRestaurant(Long id, User user, String nitRestaurant, String position, String active) {
        this.id = id;
        this.user = user;
        this.nitRestaurant = nitRestaurant;
        this.position = position;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNitRestaurant() {
        return nitRestaurant;
    }

    public void setNitRestaurant(String nitRestaurant) {
        this.nitRestaurant = nitRestaurant;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
