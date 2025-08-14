package com.demo.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Journal> journalEntries = new ArrayList<>();
}

/*
mappedBy = "user" → matches the field name in the Journal entity.
cascade = CascadeType.ALL → when you save/delete a user, it applies to journals too.
orphanRemoval = true → removing a journal from the list deletes it from DB and also deleted from respective
userid.
*/

