package com.winterhold.mvc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @Column(name = "Code", nullable = false, length = 20)
    private String id;

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryName", nullable = false)
    private Category categoryName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AuthorId", nullable = false)
    private Author author;

    @Column(name = "IsBorrowed", nullable = false)
    private Boolean isBorrowed = false;

    @Column(name = "Summary", length = 500)
    private String summary;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "TotalPage")
    private Integer totalPage;

    @OneToMany(mappedBy = "bookCode")
    private Set<Loan> loans = new LinkedHashSet<>();

    public Book(String id, String title, Category categoryName, Author author, String summary, LocalDate releaseDate, Integer totalPage) {
        this.id = id;
        this.title = title;
        this.categoryName = categoryName;
        this.author = author;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }
}