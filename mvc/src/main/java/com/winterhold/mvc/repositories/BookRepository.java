package com.winterhold.mvc.repositories;

import com.winterhold.mvc.dtos.author.AuthorDetailGridDTO;
import com.winterhold.mvc.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findByAuthorId(Long id, Pageable pageable);

//    @Query("""
//            SELECT new com.winterhold.mvc.dtos.author.AuthorDetailGridDTO(b.id, b.title, b.categoryNameId, b.isBorrowed, b.releaseDate, b.totalPage)
//            FROM Book AS b
//            INNER JOIN b.Author as a
//            WHERE a.id = :id
//            AND b.title LIKE %:title%
//            AND b.categoryNameId LIKE %:categoryNameId%
//            AND b.isBorrowed LIKE %:isBorrowed%
//            AND b.releaseDate LIKE %:releaseDate%
//            AND b.totalPage LIKE %:totalPage%
//            """)
//    Page<AuthorDetailGridDTO> findAllBookByAuthor(Long id, String title, String categoryNameId, String isBorrowed, LocalDate releaseDate, Integer totalPage, Pageable pageable);
}
