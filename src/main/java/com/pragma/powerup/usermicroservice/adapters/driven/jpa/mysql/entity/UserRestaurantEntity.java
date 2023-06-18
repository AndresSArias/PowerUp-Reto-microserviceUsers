package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserRestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity userEntity;
    @Column(nullable = false)
    private String nitRestaurant;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String active;

}
