package com.winterhold.mvc.dtos.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanGridDTO implements Serializable {
    private Long id;
    private String title;
    private String customerFullName;
    private String loanDate;
    private String dueDate;
    private String returnDate;
    private String note;

    public static List<LoanGridDTO> convert(List<LoanDTO> loanContent){
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<LoanGridDTO> result = new ArrayList<>();
        for (LoanDTO loan : loanContent){
            result.add(new LoanGridDTO(
                    loan.getId(),
                    loan.getBookTitle(),
                    loan.getCustomerFullName(),
                    loan.getLoanDate() == null ? "-" : loan.getLoanDate().format(indoFormat),
                    loan.getDueDate() == null ? "-" : loan.getDueDate().format(indoFormat),
                    loan.getReturnDate() == null ? "-" : loan.getReturnDate().format(indoFormat),
                    loan.getNote()));
        }
        return result;
    }
}
