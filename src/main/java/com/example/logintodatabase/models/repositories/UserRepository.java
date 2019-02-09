package com.example.logintodatabase.models.repositories;

import com.example.logintodatabase.models.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    //query by method name
    boolean existsByName(String name);

    //query by SQL
    @Query(value = "SELECT * FROM `user` WHERE `name` = ?1", nativeQuery = true)
    Optional<UserEntity> getUserByUsername(String username);
}
