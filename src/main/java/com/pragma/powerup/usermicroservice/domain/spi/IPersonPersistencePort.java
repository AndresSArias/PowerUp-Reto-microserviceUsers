package com.pragma.powerup.usermicroservice.domain.spi;

public interface IPersonPersistencePort {
    void savePerson(Person person);
}
