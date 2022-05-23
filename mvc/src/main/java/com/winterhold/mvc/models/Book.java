package com.winterhold.mvc.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(Boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

}