package com.example.logintodatabase.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class ContactEntity {
    public @Id @GeneratedValue int id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; //
}
