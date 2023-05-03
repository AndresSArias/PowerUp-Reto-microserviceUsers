package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    //Metodos que interactuan con el dominio donde envía directamente propiedades, o objetos de clase del modelo, ya que fueron transformadas con el mapper
    void saveUserOwner(User user);
    void deleteUser(User user);
    List<User> getAllProviders(int page);
    User getProvider(Long id);
    User getEmployee(Long id);
    User getClient(Long id);
}
