package com.winterhold.mvc.dtos.loan;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LoanDTO implements Serializable {
    private final Long id;
    private final String bookTitle;
    private final String customerFullName;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private final LocalDate returnDate;
    private final String note;
}
