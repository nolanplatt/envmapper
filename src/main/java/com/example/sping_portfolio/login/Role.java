package com.example.sping_portfolio.login;

import javax.persistence.*;

// annotations to connect to local mysql database "role"
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // constructors
    public Role() {}
    public Role(String name) {
        this.name = name;
    }

    // getter methods
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // setter methods
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
