package com.example.logintodatabase.models.services;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.entities.ContactEntity;
import com.example.logintodatabase.models.entities.UserEntity;
import com.example.logintodatabase.models.mappers.UserToUserEntityMapper;
import com.example.logintodatabase.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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



//
//    @Transactional
//    public void test() {
//        Optional<UserEntity> userEntity = userRepository.findById(userSession.getUserEntity().getId());
//        UserEntity withoutOptional = userEntity.get();
//
//        ContactEntity contactEntity = new ContactEntity();
//        contactEntity.setEmail("asd");
//        contactEntity.setSurname("asdasd");
//        contactEntity.setName("asdasdsad");
//        contactEntity.setPhone("213213213");
//        contactEntity.setUser(withoutOptional);
//
//        withoutOptional.getContacts().add(contactEntity);
//    }

}
