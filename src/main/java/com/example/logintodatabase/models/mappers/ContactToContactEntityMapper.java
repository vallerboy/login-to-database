package com.example.logintodatabase.models.mappers;

import com.example.logintodatabase.models.entities.ContactEntity;
import com.example.logintodatabase.models.forms.AddContactForm;

public class ContactToContactEntityMapper extends Mapper<AddContactForm, ContactEntity> {
    @Override
    public ContactEntity map(AddContactForm key) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(key.getName());
        contactEntity.setSurname(key.getSurname());
        contactEntity.setPhone(key.getPhone());
        contactEntity.setEmail(key.getEmail());

        return contactEntity;
    }
}
