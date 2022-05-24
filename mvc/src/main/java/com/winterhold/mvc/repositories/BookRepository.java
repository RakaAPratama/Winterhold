package com.winterhold.mvc.repositories;


import com.winterhold.mvc.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findByAuthorId(Long id, Pageable pageable);
    Page<Book> findByCategoryName_idAndTitleContainingAndAuthor_fullNameContaining(String category, String title, String author, Pageable pageable);
}
