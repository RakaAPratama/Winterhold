package com.winterhold.mvc.dtos.author;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AuthorDTO implements Serializable {
    private final Long id;
    private final String title;
    private final String fullName;
    private final LocalDate birthDate;
    private final LocalDate deceasedDate;
    private final String education;
    private final String summary;
}
