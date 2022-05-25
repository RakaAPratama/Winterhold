package com.winterhold.mvc.repositories;


import com.winterhold.mvc.dtos.book.BookDTO;
import com.winterhold.mvc.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findByAuthorId(Long id, Pageable pageable);
    Page<Book> findByCategoryName_idAndTitleContainingAndAuthor_fullNameContaining(String category, String title, String author, Pageable pageable);

    @Query("""
            SELECT new com.winterhold.mvc.dtos.book.BookDTO
            (b.id, b.title, b.isBorrowed)
            FROM Book b
            WHERE b.isBorrowed = false
            """)
    List<BookDTO> findAllBookAvailable();

    List<Book> findByLoans_Id(Long id);
}
