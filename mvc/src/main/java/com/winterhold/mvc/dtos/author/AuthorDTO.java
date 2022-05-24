package com.winterhold.mvc.dtos.author;

import com.winterhold.mvc.models.Author;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDTO implements Serializable {
    private final Long id;
    private final String title;
    private final String fullName;
    private final LocalDate birthDate;
    private final LocalDate deceasedDate;
    private final String education;
    private final String summary;

    public static List<AuthorDTO> convertAuthor(List<Author> authors) {
        List<AuthorDTO> result = new ArrayList<>();

        for (Author author : authors) {
            long age = ChronoUnit.YEARS.between(author.getBirthDate(), LocalDate.now());
            result.add(new AuthorDTO(
                    author.getId(),
                    author.getTitle(),
                    author.getFullName(),
                    author.getBirthDate(),
                    author.getDeceasedDate(),
                    author.getEducation(),
                    author.getSummary()));
        }
        return result;
    }
}
