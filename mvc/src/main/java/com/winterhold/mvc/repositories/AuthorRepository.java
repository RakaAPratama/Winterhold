package com.winterhold.mvc.repositories;

import com.winterhold.mvc.dtos.author.AuthorDTO;
import com.winterhold.mvc.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(""" 
            SELECT new com.winterhold.mvc.dtos.author.AuthorDTO
            (a.id, a.title, CONCAT(a.title, ' ',a.firstName , ' ', a.lastName), 
            a.birthDate, a.deceasedDate,
            a.education, a.summary)
            FROM Author a
            WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE %:fullName%                             
            """)
    Page<AuthorDTO> findAllAuthor(String fullName, Pageable page);
}
