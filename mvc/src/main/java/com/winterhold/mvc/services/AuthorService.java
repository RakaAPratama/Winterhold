package com.winterhold.mvc.services;

import com.winterhold.mvc.dtos.author.AuthorDTO;
import com.winterhold.mvc.dtos.author.UpdateInsertAuthorDTO;
import com.winterhold.mvc.models.Author;
import com.winterhold.mvc.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

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
}
