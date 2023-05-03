package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//un repositoruo es una interfaz que extiende de JPaRepositui<a,b> donde a es un Entitu y b el tipo de primary key
public interface IUserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByPersonEntityIdAndRoleEntityId(Long idPerson, Long idRole);
    void deleteByPersonEntityIdAndRoleEntityId(Long idPerson, Long idRole);
    List<UserEntity> findAllByRoleEntityId(Long idRole, Pageable pageable);
    List<UserEntity> findAllByPersonEntityId(Long idPerson);

    Optional<UserEntity> findByNumberDocument(String number_document);
    boolean existsByEmail(String email);
}
