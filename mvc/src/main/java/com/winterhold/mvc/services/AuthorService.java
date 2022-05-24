package com.winterhold.mvc.services;

import com.winterhold.mvc.dtos.author.AuthorDTO;
import com.winterhold.mvc.dtos.author.AuthorDetailGridDTO;
import com.winterhold.mvc.dtos.author.UpdateInsertAuthorDTO;
import com.winterhold.mvc.models.Author;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.repositories.AuthorRepository;
import com.winterhold.mvc.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private final int PAGE_LIMIT = 5;

    public Page<AuthorDTO> findAllAuthor(int page, String fullName){
        Page<AuthorDTO> allAuthors = authorRepository.findAllAuthor(fullName, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
        return allAuthors;
    }

    public UpdateInsertAuthorDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        return new UpdateInsertAuthorDTO(
                author.getId(),
                author.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthDate(),
                author.getDeceasedDate(),
                author.getEducation(),
                author.getSummary());
    }

    public void saveAuthor(UpdateInsertAuthorDTO dto) {

        Author author = new Author(
                dto.getId(),
                dto.getTitle(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getDeceasedDate(),
                dto.getEducation(),
                dto.getSummary());
        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Page<Book> findAllBooksByAuthor(Long id, int page){
        Page<Book> allBooks = bookRepository.findByAuthorId(id, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
        return allBooks;
    }

    public List<AuthorDTO> findAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        return AuthorDTO.convertAuthor(authors);
    }

}
