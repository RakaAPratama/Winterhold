package com.winterhold.mvc.dtos.loan;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class LoanDetailDTO implements Serializable {
    private final Long id;
    private final String customerNumberId;
    private final String customerNumberFullName;
    private final String customerNumberPhone;
    private final LocalDate customerNumberMembershipExpireDate;
    private final String bookTitle;
    private final String bookCategoryName;
    private final String bookAuthorFullName;
    private final Integer bookCategoryFloor;
    private final String bookCategoryIsle;
    private final String bookCategoryBay;
}
