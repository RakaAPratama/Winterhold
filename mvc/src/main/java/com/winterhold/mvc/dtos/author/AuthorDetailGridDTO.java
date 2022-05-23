package com.winterhold.mvc.dtos.author;

import com.winterhold.mvc.models.Book;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class AuthorDetailGridDTO implements Serializable {
    private final String id;
    private final String title;
    private final String categoryName;
    private final String isBorrowed;
    private final String releaseDate;
    private final Integer totalPage;

    public static List<AuthorDetailGridDTO> convert(List<Book> content) {
        List<AuthorDetailGridDTO> result = new ArrayList<>();
        DateTimeFormatter indoFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("id-ID"));

        for (Book book : content) {
            result.add(new AuthorDetailGridDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getCategoryName().getId(),
                    book.getIsBorrowed() == false ? "Available" : "Borrowed",
                    book.getReleaseDate().format(indoFormat),
                    book.getTotalPage()
            ));
        }

        return result;
    }
}
