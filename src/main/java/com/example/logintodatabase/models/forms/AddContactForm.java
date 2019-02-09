package com.example.logintodatabase.models.forms;


import lombok.Data;

@Data
public class AddContactForm {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
