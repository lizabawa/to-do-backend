package com.example.todobackend.model;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class List {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String title;

    @Column
    private String description;
}
