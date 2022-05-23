package com.winterhold.mvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @Column(name = "Name", nullable = false, length = 100)
    private String id;

    @Column(name = "Floor", nullable = false)
    private Integer floor;

    @Column(name = "Isle", nullable = false, length = 10)
    private String isle;

    @Column(name = "Bay", nullable = false, length = 10)
    private String bay;

    @OneToMany(mappedBy = "categoryName")
    private Set<Book> books = new LinkedHashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}