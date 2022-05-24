package com.winterhold.mvc.controllers;

import com.winterhold.mvc.dtos.author.AuthorDTO;
import com.winterhold.mvc.dtos.author.AuthorDetailGridDTO;
import com.winterhold.mvc.dtos.book.UpdateInsertBookDTO;
import com.winterhold.mvc.dtos.category.CategoryDTO;
import com.winterhold.mvc.dtos.category.UpdateInsertCategoryDTO;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.models.Category;
import com.winterhold.mvc.services.AuthorService;
import com.winterhold.mvc.services.BookService;
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
@RequestMapping("book")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String category,
                        Model model){

        Page<Category> categories = bookService.findAllCategories(page, category);
        List<CategoryDTO> categoryDTOS = CategoryDTO.convert(categories.getContent());

        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("breadCrumbs", "Book / Index");
        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("category", category);

        return "book/book-index";
    }

    @GetMapping("category-upsert-form")
    public String updateInsertCategoryForm(@RequestParam(required = false) String category, Model model){
        if (category != null){
            model.addAttribute("category", bookService.findCategoryById(category));
            model.addAttribute("breadCrumbs", "Book / Update Category");
        } else {
            model.addAttribute("category", new UpdateInsertCategoryDTO());
            model.addAttribute("breadCrumbs", "Book / Insesrt Category");
        }
        return "book/book-upsert-category";
    }

    @PostMapping("category-upsert")
    public String updateInsert(@Valid @ModelAttribute("category")UpdateInsertCategoryDTO category, BindingResult bindingResult,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "book/book-upsert-category";
        }
        bookService.saveCategory(category);
        return "redirect:/book/index";
    }

    @GetMapping("category-delete")
    public String deleteCategory(@RequestParam String category,
                                 RedirectAttributes redirectAttributes){
        try{
            bookService.deleteCategoryById(category);
            redirectAttributes.addFlashAttribute("succes", "Delete category successfully");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Cannot delete category, Category is used");
            return "redirect:/book/index";
        }
        return "redirect:/book/index";
    }

    @GetMapping("books")
    public String books(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String category,
                        @RequestParam(defaultValue = "") String title,
                        @RequestParam(defaultValue = "") String author,
                        Model model){
        Page<Book> allBooks = bookService.findAllBookByCategory(category, title, author, page);
        List<AuthorDetailGridDTO> booksGrid = AuthorDetailGridDTO.convert(allBooks.getContent());
        model.addAttribute("books", booksGrid);
        model.addAttribute("breadCrumbs", "Book / Books");
        model.addAttribute("totalPage", allBooks.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("category", category);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        return "book/book-list";
    }

    @GetMapping("book-upsert-form")
    private String updateInsertBookForm(@RequestParam String category,
                                        @RequestParam(required = false) String bookId,
                                        Model model){
        model.addAttribute("category", category);
        if (bookId != null){
            model.addAttribute("book", bookService.findBookById(bookId));
            model.addAttribute("breadCrumbs", "Book / Update Book");
        }else{
            model.addAttribute("book", UpdateInsertBookDTO.insertNewBook(new UpdateInsertBookDTO(), category));
            model.addAttribute("breadCrumbs", "Book / Insert New Book");
        }
        List<AuthorDTO> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "book/book-upsert-book";
    }

    @PostMapping("book-upsert")
    public String updateInsert(@Valid @ModelAttribute("book") UpdateInsertBookDTO newBook,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam String category){
        newBook.setCategoryName(category);

        if (bindingResult.hasErrors()){
            List<AuthorDTO> authors = authorService.findAllAuthors();
            model.addAttribute("authors", authors);
            model.addAttribute("category", category);
            model.addAttribute("book", newBook);
            return "book/book-upsert-book";
        }
        bookService.saveBook(newBook);
        return "redirect:/book/books?category=" + category;
    }

    @GetMapping("book-delete")
    public String delete(@RequestParam String bookId,
                         @RequestParam String category){
        bookService.deleteBook(bookId);
        return "redirect:/books?category=" + category;
    }
}
