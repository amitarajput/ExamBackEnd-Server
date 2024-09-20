package com.exam.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;
    private String title;
    private  String description;

    //relationship
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)//category responsibility to create data in table
    @JsonIgnore

    //category has many quizzes mapped by category
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    //constructors

    public Category() {
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //getter setter


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
