package com.ms.ex.booksearch.booksearch.service;

import com.ms.ex.booksearch.booksearch.dto.BooleanYn;
import com.ms.ex.booksearch.booksearch.common.util.StringUtils;
import com.ms.ex.booksearch.booksearch.dto.BookDto;
import com.ms.ex.booksearch.booksearch.dto.PageDto;
import com.ms.ex.booksearch.booksearch.entity.Book;
import com.ms.ex.booksearch.booksearch.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<Book> getBookList(String title, String sortName, String sortOption, int page) {
        log.info("검색어: {}", StringUtils.isEmpty(title) ? "공백" : title);

        Direction sort = "DESC".equalsIgnoreCase(sortOption) ? DESC : ASC;
        PageRequest pageRequest = new PageRequest(page, PageDto.PER_PAGE, sort, sortName);

        if (StringUtils.isEmpty(title)) {
            return bookRepository.findAll(pageRequest);
        }else{
            return bookRepository.findAllByTitleContaining(title, pageRequest);
        }
    }

    public Page<Book> getBookPage(String title, int page) {
        PageRequest pageRequest = new PageRequest(page, PageDto.PER_PAGE);

        if (StringUtils.isEmpty(title)) {
            return  bookRepository.findAll(pageRequest);
        }else{
            return bookRepository.findAllByTitleContaining(title, pageRequest);
        }
    }

    public void save(BookDto bookDto) {
        Book book = new Book(bookDto);
        bookRepository.save(book);
    }

    public Book getBookById(Long id){
        return bookRepository.findOne(id);
    }

    @Transactional
    public void saveBookmark(long id, boolean bookmarked) {
        Book book = bookRepository.findOne(id);
        book.setBookmarked(BooleanYn.of(bookmarked));
    }

    public void deleteBook(long id) {
        bookRepository.delete(id);
    }
}
