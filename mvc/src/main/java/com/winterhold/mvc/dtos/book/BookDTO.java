package com.winterhold.mvc.dtos.book;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDTO implements Serializable {
    private final String id;
    private final String title;
    private final Boolean isBorrowed;
}
