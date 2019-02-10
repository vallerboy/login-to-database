package com.example.logintodatabase.models.forms;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddContactForm {
    @Pattern(regexp = "[A-Za-z]+")
    @Size(min = 3, max =  30)
    private String name;
    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String surname;
    @Pattern(regexp = "[0-9]{9}")
    private String phone;
    @Email
    private String email;
}
