package com.ms.ex.booksearch.booksearch.controller;

import com.ms.ex.booksearch.booksearch.dto.BookDto;
import com.ms.ex.booksearch.booksearch.dto.PageDto;
import com.ms.ex.booksearch.booksearch.entity.Book;
import com.ms.ex.booksearch.booksearch.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BookApiController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getBookList(@RequestParam("title") String title,
                                  @RequestParam(value = "sortName", defaultValue = "registerDatetime", required = false) String sortName,
                                  @RequestParam(value = "sortOption", defaultValue = "desc", required = false) String sortOption,
                                  @RequestParam(defaultValue = "0", required = false) int page){
        return bookService.getBookList(title, sortName, sortOption, page).getContent();
    }

    @GetMapping("/book-pages")
    public PageDto<Book> getBookPage(@RequestParam(value = "title", defaultValue = "", required = false) String title,
                                     @RequestParam(value = "sortName", defaultValue = "registerDatetime", required = false) String sortName,
                                     @RequestParam(value = "sortOption", defaultValue = "desc", required = false) String sortOption,
                                     @RequestParam(value = "page", defaultValue = "0", required = false) int page){
        Page<Book> bookPage = bookService.getBookList(title, sortName, sortOption, page);

        return PageDto.of(bookPage.getContent(), page, bookPage.getTotalElements());
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookDto bookDto){
        log.debug("{}", bookDto);
        bookService.save(bookDto);
    }

    @GetMapping("/books/{id:[0-9]+}")
    public Book getBook(@PathVariable("id") Long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/bookmark/{id:[0-9]+}")
    public void saveBookmark(@PathVariable("id") long id,
                             @RequestParam(value = "bookmarked", required = true) boolean bookmarked){
        bookService.saveBookmark(id, bookmarked);
    }

    @DeleteMapping("/books/{id:[0-9]+}")
    public void deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
    }

}
