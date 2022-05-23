package com.winterhold.mvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @Column(name = "Username", nullable = false, length = 20)
    private String id;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}