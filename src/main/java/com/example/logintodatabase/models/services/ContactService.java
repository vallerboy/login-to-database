package com.example.logintodatabase.models.services;

import com.example.logintodatabase.models.entities.ContactEntity;
import com.example.logintodatabase.models.forms.AddContactForm;
import com.example.logintodatabase.models.mappers.ContactToContactEntityMapper;
import com.example.logintodatabase.models.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {


    ContactRepository contactRepository;
    UserSession userSession;
    final
    UserService userService;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserSession userSession, UserService userService) {
        this.contactRepository = contactRepository;
        this.userSession = userSession;
        this.userService = userService;
    }

    public Page<ContactEntity> getAllContactsForLoginUser(int page){
        if(!userSession.isLogin()){
            throw new IllegalStateException("user not login");
        }
        return contactRepository.findByUser(userSession.getUserEntity(), PageRequest.of(page, 2, Sort.by("id").descending()));
    }

    public void addContact(AddContactForm addContactForm) {
        ContactEntity contactEntity = new ContactToContactEntityMapper().map(addContactForm);
        contactEntity.setUser(userSession.getUserEntity());

        contactRepository.save(contactEntity);
    }

    public void deleteContact(int id) {
        if(!userSession.isLogin()){
            return;
        }

        if (!isLoginUserOwnerOfContactWithId(id)) return;

        contactRepository.deleteById(id);
    }

    public boolean isLoginUserOwnerOfContactWithId(int id) {
        return contactRepository.existsByUser_IdAndId(userSession.getUserEntity().getId(), id);
    }
}
