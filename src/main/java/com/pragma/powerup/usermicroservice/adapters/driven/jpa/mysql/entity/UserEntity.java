package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//Esto es una entidad de la bd, con @entitym @tabke con el parametro name para el nom de la tabla en la bd y el resto es longbod
public class UserEntity {
    //Se identifica la primary key
    @Id
    //La forma de generarse esa id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //La relación de esa entidad con las otras que hay.
    //ManyToOne dice que de muchos a uno, es decir que acá en la tabla user, su columna "id_person" recibira muchos valores pks de la relación con su tabla
    @ManyToOne
    @JoinColumn(name = "id_person")
    //donde directamente dice el atributo una entidad que es PersonEntity
    private PersonEntity personEntity;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;
}
