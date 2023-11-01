package com.example.todobackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> itemList;

    public Category() {
    }

    public Category(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
