package com.winterhold.mvc.controllers;

import com.winterhold.mvc.dtos.book.BookDTO;
import com.winterhold.mvc.dtos.customer.CustomerDTO;
import com.winterhold.mvc.dtos.loan.LoanDTO;
import com.winterhold.mvc.dtos.loan.LoanGridDTO;
import com.winterhold.mvc.dtos.loan.UpdateInsertLoanDTO;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.services.BookService;
import com.winterhold.mvc.services.CustomerService;
import com.winterhold.mvc.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("loan")
public class LoanController {
    private LoanService loanService;
    private CustomerService customerService;
    private BookService bookService;

    @Autowired
    public LoanController(LoanService loanService, CustomerService customerService, BookService bookService) {
        this.loanService = loanService;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @RequestMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String titleBook,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model){

        Page<LoanDTO> allLoans = loanService.findAllLoan(page, titleBook, fullName);
        List<LoanGridDTO> loanGrid = LoanGridDTO.convert(allLoans.getContent());

        model.addAttribute("loans", loanGrid);
        model.addAttribute("breadCrumbs", "Loan / Index");
        model.addAttribute("totalPage", allLoans.getTotalPages());
        model.addAttribute("titleBook", titleBook);
        model.addAttribute("fullName", fullName);
        model.addAttribute("page", page);

        return "loan/loan-index";
    }

    @GetMapping("upsert-form")
    public String updateInsertForm(@RequestParam(required = false) Long id, Model model){
        List<CustomerDTO> customers = customerService.findAllCustomerNotExp();
        model.addAttribute("customers", customers);

        if (id != null){
            List<Book> books = loanService.findBookByLoanId(id);
            model.addAttribute("books", books);
            model.addAttribute("loan", loanService.findById(id));
            model.addAttribute("breadCrumbs", "Loan / Update");
        }else{
            List<BookDTO> books = bookService.findAllBookAvailable();
            model.addAttribute("books", books);
            model.addAttribute("loan", new UpdateInsertLoanDTO());
            model.addAttribute("breadCrumbs", "Loan / Insert");
        }
        return "loan/loan-upsert";
    }

    @PostMapping("upsert")
    public String updateInsert(@Valid @ModelAttribute("loan") UpdateInsertLoanDTO loan,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            List<CustomerDTO> customers = customerService.findAllCustomerNotExp();
            List<BookDTO> books = bookService.findAllBookAvailable();
            model.addAttribute("customers", customers);
            model.addAttribute("books", books);
            model.addAttribute("loan", loan);
            return "loan/loan-upsert";
        }
        model.getAttribute("loan");
        if (loan.getId() != null){
            loanService.updateLoan(loan);
            redirectAttributes.addFlashAttribute("success", "Loan has been updated");
        }else{
            loanService.insertNewLoan(loan);
            redirectAttributes.addFlashAttribute("success", "New Loan has been inserted");
        }

        return "redirect:/loan/index";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(required = false) Long id, Model model){
        if (id != null){
            model.addAttribute("loanDetail", loanService.findDetailById(id));
            model.addAttribute("breadCrumbs", "Loan / Detail Loan");
        }
        return "loan/loan-detail";
    }

    @GetMapping("return")
    public String returnBook(@RequestParam(required = false) Long id,
                             RedirectAttributes redirectAttributes,
                             Model model){
        if (id != null) {
            loanService.returnBook(id);
            redirectAttributes.addFlashAttribute("succes", "Book has been returned");
        }
        return "redirect:/loan/index";
    }
}
