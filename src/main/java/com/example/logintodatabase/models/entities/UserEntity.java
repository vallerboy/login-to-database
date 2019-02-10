package com.example.logintodatabase.models.entities;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    public enum AccountStatus {
        ACTIVATED, NOT_ACTIVATED, BANNED;
    }

    @Id @GeneratedValue private Integer id;
    private String name;
    private String password;
    private @Column(name = "entry_date") LocalDateTime time;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {})
    List<ContactEntity> contacts;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


//    @PreRemove
//    public void prepareRemove() {
//        for (int i = 0; i < contacts.size(); i++) {
//            contacts.get(i).setUser(null);
//        }
//    }
}
