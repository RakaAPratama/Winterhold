package com.winterhold.mvc.dtos.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateInsertAuthorDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Title tidak boleh kosong")
    private String title;

    @NotBlank(message = "First Name tidak boleh kosong")
    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birth Date tidak boleh kosong")
    private LocalDate birthDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deceasedDate;
    private String education;
    private String summary;
    private Set<String> bookIds;

    public UpdateInsertAuthorDTO(Long id, String title, String firstName, String lastName, LocalDate birthDate, LocalDate deceasedDate, String education, String summary) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deceasedDate = deceasedDate;
        this.education = education;
        this.summary = summary;
    }
}
