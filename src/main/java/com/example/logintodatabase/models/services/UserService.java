package com.example.logintodatabase.models.services;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.mappers.UserToUserEntityMapper;
import com.example.logintodatabase.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(new UserToUserEntityMapper().map(user));
    }

    public boolean checkIfUsernameIsFree(User user){
        userRepository.existsByName(user.getName());
        return false;
    }

}
