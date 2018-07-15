package com.ms.ex.booksearch.booksearch.service;

import com.ms.ex.booksearch.booksearch.dto.BookDto;
import com.ms.ex.booksearch.booksearch.dto.BookSearchRequest;

public interface BookSearchApiService {
    BookDto getBookList(BookSearchRequest bookSearchRequest);
}
