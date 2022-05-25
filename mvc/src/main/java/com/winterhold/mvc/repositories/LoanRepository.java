package com.winterhold.mvc.repositories;

import com.winterhold.mvc.dtos.loan.LoanDTO;
import com.winterhold.mvc.models.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("""
            SELECT new com.winterhold.mvc.dtos.loan.LoanDTO
            (lo.id, lo.bookCode.title, lo.customerNumber.fullName, lo.loanDate, lo.dueDate, lo.returnDate, lo.note)
            FROM Loan lo
            WHERE lo.bookCode.title LIKE %:titleBook%
            AND lo.customerNumber.fullName LIKE %:fullName%
            """)
    Page<LoanDTO> findAllLoan(String titleBook, String fullName, Pageable pageable);
}
