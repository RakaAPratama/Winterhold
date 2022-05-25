package com.winterhold.mvc.dtos.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInsertLoanDTO implements Serializable {
    private Long id;
    @NotBlank(message = "Customer Number tidak boleh kosong")
    private String customerNumberId;
    @NotBlank(message = "Book Code tidak boleh kosong")
    private String bookCodeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Load Date tidak boleh kosong")
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String note;
}
