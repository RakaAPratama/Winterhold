package com.winterhold.mvc.dtos.author;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorGridDTO {
    private final Long id;
    private final String fullName;
    private final long age;
    private final String status;
    private final String education;

    public static List<AuthorGridDTO> convert(List<AuthorDTO> authorDtos) {
        List<AuthorGridDTO> result = new ArrayList<>();

        for (AuthorDTO authorDto : authorDtos) {
            long age  = ChronoUnit.YEARS.between(authorDto.getBirthDate(), LocalDate.now());
            result.add(new AuthorGridDTO(
                    authorDto.getId(),
                    authorDto.getFullName(),
                    age,
                    authorDto.getDeceasedDate() == null ? "Alive" : "Deceased",
                    authorDto.getEducation()));
        }
        return result;
    }
}
