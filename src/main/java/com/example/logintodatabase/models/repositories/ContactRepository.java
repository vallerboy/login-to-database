package com.example.logintodatabase.models.repositories;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.entities.ContactEntity;
import com.example.logintodatabase.models.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {
   // List<ContactEntity>  findByUser_Id(int id);
    Page<ContactEntity> findByUser(UserEntity id, Pageable pageable);
    boolean existsByUser_IdAndId(int user_id, int id);
}
