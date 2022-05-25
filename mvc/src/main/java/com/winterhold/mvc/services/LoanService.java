package com.winterhold.mvc.services;

import com.winterhold.mvc.dtos.loan.LoanDTO;
import com.winterhold.mvc.dtos.loan.LoanDetailDTO;
import com.winterhold.mvc.dtos.loan.UpdateInsertLoanDTO;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.models.Customer;
import com.winterhold.mvc.models.Loan;
import com.winterhold.mvc.repositories.BookRepository;
import com.winterhold.mvc.repositories.CustomerRepository;
import com.winterhold.mvc.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private LoanRepository loanRepository;
    private CustomerRepository customerRepository;
    private BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    private final int PAGE_LIMIT = 5;

    public Page<LoanDTO> findAllLoan(int page, String titleBook, String fullName){
        return loanRepository.findAllLoan(
                titleBook, fullName, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
    }

    public UpdateInsertLoanDTO findById(Long id){
        Loan dataLoan = loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        return new UpdateInsertLoanDTO(
                dataLoan.getId(),
                dataLoan.getCustomerNumber().getId(),
                dataLoan.getBookCode().getId(),
                dataLoan.getLoanDate(),
                dataLoan.getDueDate(),
                dataLoan.getReturnDate(),
                dataLoan.getNote());
    }

    public void insertNewLoan (UpdateInsertLoanDTO newLoan){
        Customer customer = customerRepository.findById(newLoan.getCustomerNumberId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Book book = bookRepository.findById(newLoan.getBookCodeId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Loan insertLoan = new Loan(
                newLoan.getId(),
                customer,
                book,
                newLoan.getLoanDate(),
                newLoan.getLoanDate().plusDays(5),
                newLoan.getReturnDate(),
                newLoan.getNote());
        loanRepository.save(insertLoan);
        book.setIsBorrowed(true);
        bookRepository.save(book);
    }

    public void updateLoan (UpdateInsertLoanDTO loan){
        customerRepository.findById(loan.getCustomerNumberId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        bookRepository.findById(loan.getBookCodeId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Loan updatedLoan = loanRepository.findById(loan.getId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        updatedLoan.setLoanDate(loan.getLoanDate());
        updatedLoan.setDueDate(loan.getLoanDate().plusDays(5));
        updatedLoan.setNote(loan.getNote());
        loanRepository.save(updatedLoan);
    }

    public List<Book> findBookByLoanId(Long id){
        return bookRepository.findByLoans_Id(id);
    }

    public LoanDetailDTO findDetailById(Long id){
        Loan dataLoan = loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        return new LoanDetailDTO(
                dataLoan.getId(),
                dataLoan.getCustomerNumber().getId(),
                dataLoan.getCustomerNumber().getFullName(),
                dataLoan.getCustomerNumber().getPhone(),
                dataLoan.getCustomerNumber().getMembershipExpireDate(),
                dataLoan.getBookCode().getTitle(),
                dataLoan.getBookCode().getCategoryName().getId(),
                dataLoan.getBookCode().getAuthor().getFullName(),
                dataLoan.getBookCode().getCategoryName().getFloor(),
                dataLoan.getBookCode().getCategoryName().getIsle(),
                dataLoan.getBookCode().getCategoryName().getBay());
    }

    public void returnBook(Long id){
        Loan dataLoan = loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        Book book = dataLoan.getBookCode();
        book.setIsBorrowed(false);
        bookRepository.save(book);
        dataLoan.setReturnDate(LocalDate.now());
        loanRepository.save(dataLoan);
    }
}
