package com.ms.ex.booksearch.booksearch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookSearchRequest {
    private String query;
    private String sort;
    private String page;
    private String size;
    private String target;
    private String category;
}
