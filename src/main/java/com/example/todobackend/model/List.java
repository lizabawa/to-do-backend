package com.example.todobackend.model;

import javax.persistence.*;

@Entity
@Table(name = "lists")
public class List {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;
}
