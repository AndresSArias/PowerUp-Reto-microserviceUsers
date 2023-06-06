package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRestaurantRepository extends JpaRepository<UserRestaurantEntity,Long> {

    @Query(value = "SELECT id_user FROM user_restaurant WHERE id_user = :idUser", nativeQuery = true)
    Optional<String> findByIdUser(@Param("idUser") String idUser);
}
