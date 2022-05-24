package com.winterhold.mvc.dtos.author;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class AuthorGridDTO {
    private Long id;
    private String fullName;
    private String birthDate;
    private long age;
    private String status;
    private String education;
    private String summary;

    public AuthorGridDTO(Long id, String fullName, long age, String status, String education) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.status = status;
        this.education = education;
    }

    public AuthorGridDTO(Long id, String fullName, String birthDate, String status, String education, String summary) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.status = status;
        this.education = education;
        this.summary = summary;
    }



    public static List<AuthorGridDTO> convert(List<AuthorDTO> authorDtos) {
        List<AuthorGridDTO> result = new ArrayList<>();

        for (AuthorDTO authorDto : authorDtos) {
            long age;

            if (authorDto.getDeceasedDate() == null) {
                age  = ChronoUnit.YEARS.between(authorDto.getBirthDate(), LocalDate.now());
            } else{
                age  = ChronoUnit.YEARS.between(authorDto.getBirthDate(), authorDto.getDeceasedDate());
            }
            result.add(new AuthorGridDTO(
                    authorDto.getId(),
                    authorDto.getFullName(),
                    age,
                    authorDto.getDeceasedDate() == null ? "Alive" : "Deceased",
                    authorDto.getEducation()));
        }
        return result;
    }

    public static AuthorGridDTO convertAuthor(UpdateInsertAuthorDTO author){
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        return new AuthorGridDTO(
                author.getId(),
                author.getTitle() + " " + author.getFirstName() + " " + author.getLastName(),
                author.getBirthDate().format(indoFormat),
                author.getDeceasedDate() == null ? "-" : author.getDeceasedDate().format(indoFormat),
                author.getEducation(),
                author.getSummary());
    }
}
