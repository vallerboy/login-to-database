package com.example.logintodatabase.models.repositories;

import com.example.logintodatabase.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    //query by method name
    boolean existsByName(String name);
}
