package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.model.UserRestaurant;

public interface IUserServicePort {
    UserEntity saveUserOwner(User user);

    UserEntity saveUserEmployee(User user, UserRestaurant userRestaurant, String onwerDNI, String token);

    UserEntity saveUserCustomer(User user);

    User getUserByDocument (String numberDocument);
}
