package com.libraryhub.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_last_name", nullable = false)
    private String authorLastName;

    @Column(name = "author_initials", nullable = false, length = 10)
    private String authorInitials;

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "copies_count")
    private int copiesCount;
}