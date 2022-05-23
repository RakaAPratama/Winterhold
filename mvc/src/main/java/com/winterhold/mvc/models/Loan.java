package com.winterhold.mvc.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CustomerNumber", nullable = false)
    private Customer customerNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BookCode", nullable = false)
    private Book bookCode;

    @Column(name = "LoanDate", nullable = false)
    private LocalDate loanDate;

    @Column(name = "DueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    @Column(name = "Note", length = 500)
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Book getBookCode() {
        return bookCode;
    }

    public void setBookCode(Book bookCode) {
        this.bookCode = bookCode;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}