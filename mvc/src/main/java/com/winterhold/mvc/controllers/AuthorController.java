package com.winterhold.mvc.controllers;

import com.winterhold.mvc.dtos.author.AuthorDTO;
import com.winterhold.mvc.dtos.author.AuthorDetailGridDTO;
import com.winterhold.mvc.dtos.author.AuthorGridDTO;
import com.winterhold.mvc.dtos.author.UpdateInsertAuthorDTO;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("index")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "") String fullName,
                       Model model){
        Page<AuthorDTO> allAuthors = authorService.findAllAuthor(page, fullName);
        List<AuthorGridDTO> authorGridDTOS = AuthorGridDTO.convert(allAuthors.getContent());

        model.addAttribute("authors", authorGridDTOS);
        model.addAttribute("breadCrumbs", "AUTHOR / INDEX");
        model.addAttribute("totalPage", allAuthors.getTotalPages());
        model.addAttribute("fullName", fullName);
        model.addAttribute("page", page);
        return "author/author-index";
    }

    @GetMapping("upsert-form")
    public String upsertForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("author", authorService.findById(id));
            model.addAttribute("breadCrumbs", "AUTHOR / UPDATE AUTHOR");
        } else {
            model.addAttribute("author", new UpdateInsertAuthorDTO());
            model.addAttribute("breadCrumbs", "AUTHOR / INSERT AUTHOR");
        }
        return "author/author-upsert";
    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("author") UpdateInsertAuthorDTO author, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("author", author);
            return "author/author-upsert";
        }
        authorService.saveAuthor(author);
        return "redirect:/author/index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam(required = false) Long id, RedirectAttributes redirectAttributes) {
        if (id != null) {
            authorService.deleteAuthor(id);
            redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        }
        return "redirect:/author/index";
    }

    @GetMapping("books")
    public String books(@RequestParam Long id, Model model,
                         @RequestParam(defaultValue = "1") int page){

        UpdateInsertAuthorDTO author = authorService.findById(id);
        AuthorGridDTO authorGridDTO = AuthorGridDTO.convertAuthor(author);

        Page<Book> allBooks = authorService.findAllBooksByAuthor(id, page);
        List<AuthorDetailGridDTO> books = AuthorDetailGridDTO.convert(allBooks.getContent());
        model.addAttribute("author", authorGridDTO);
        model.addAttribute("books", books);
        model.addAttribute("breadCrumbs", "Author / Books");
        model.addAttribute("totalPage", allBooks.getTotalPages());
        model.addAttribute("page", page);
        return "author/author-books";




    }
}
