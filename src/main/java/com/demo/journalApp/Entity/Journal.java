package com.demo.journalApp.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key
    private User user;

}
