package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserRestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.model.UserRestaurant;

public interface IUserPersistencePort {
    UserEntity saveUserOwner(User user);

    UserEntity saveUserEmployee (User user);

    UserEntity saveUserCustomer (User user);

    UserRestaurantEntity saveRelationUserRestaurant (UserRestaurant userRestaurant, Long idRol);

    User getUserByDocument(String numberDocument);

}
