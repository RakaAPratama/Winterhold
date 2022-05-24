package com.winterhold.mvc.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInsertBookDTO implements Serializable {
    @NotBlank(message = "Code tidak boleh kosong")
    private String id;
    @NotBlank(message = "Title tidak boleh kosong")
    private String title;
    private String categoryName;
    @NotNull(message = "Author tidak boleh kosong")
    private Long authorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Integer totalPage;
    private String summary;

    public static UpdateInsertBookDTO insertNewBook (UpdateInsertBookDTO book, String categoryName){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new UpdateInsertBookDTO(
                book.getId(),
                book.getTitle(),
                categoryName,
                book.getAuthorId(),
                book.getReleaseDate() == null ? null : book.getReleaseDate(),
                book.getTotalPage(),
                book.getSummary()
        );
    }
}
