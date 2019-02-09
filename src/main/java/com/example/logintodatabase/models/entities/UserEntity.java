package com.example.logintodatabase.models.entities;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id @GeneratedValue private Integer id;
    private String name;
    private String password;
    private @Column(name = "entry_date") LocalDateTime time;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ContactEntity> contacts;
}
