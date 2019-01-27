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

    //boolean or enum

    /**
     *
     * @param user
     * @return return true if user was added correctly, and false otherwise
     */
    public boolean addUser(User user){
        if(!isUsernameFree(user.getName())){
            return false;
        }
        userRepository.save(new UserToUserEntityMapper().map(user));
        return true;
    }

    private boolean isUsernameFree(String nick){
       return !userRepository.existsByName(nick);
    }


}
