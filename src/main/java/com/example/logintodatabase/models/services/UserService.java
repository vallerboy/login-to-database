package com.example.logintodatabase.models.services;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.entities.UserEntity;
import com.example.logintodatabase.models.mappers.UserToUserEntityMapper;
import com.example.logintodatabase.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    HashService hashService;
    //boolean or enum

    @Autowired
    UserSession userSession;


    /**
     *
     * @param user
     * @return return true if user was added correctly, and false otherwise
     */
    public boolean addUser(User user){
        if(!isUsernameFree(user.getName())){
            return false;
        }

        user.setPassword(hashService.hashPassword(user.getPassword()));
        return userRepository.save(new UserToUserEntityMapper().map(user)) != null;

    }

    private boolean isUsernameFree(String nick){
       return !userRepository.existsByName(nick);
    }


    public boolean login(User user){
        Optional<UserEntity> userWitchTryToLogin =
                userRepository.getUserByUsername(user.getName());
        //tutaj odbedzie sie zapis do ciasteczka usera i ogolnei zapis usera zalogowanego
        if (userWitchTryToLogin.isPresent() &&
                hashService.isPasswordCorrect(userWitchTryToLogin.get().getPassword(), user.getPassword())) {

            userSession.setLogin(true);
            userSession.setUserEntity(userWitchTryToLogin.get());
            return true;
        }
        return false;
    }

}
