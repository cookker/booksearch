package com.ms.ex.booksearch.booksearch.repository;

import com.ms.ex.booksearch.booksearch.dto.BooleanYn;
import com.ms.ex.booksearch.booksearch.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책조회() {
        Book book = bookRepository.findOne(1L);
        System.out.println(book);
        assertThat(book.getPrice(), is("14900"));
    }

    @Test
    public void 책저장(){
        List<Book> bookList = bookRepository.findAll();
        int size = bookList == null ? 0 : bookList.size();

        Book book = new Book("title",
                "contents",
                "url",
                "isbn",
                "2014-11-17T00:00:00.000+09:00",
                new String[]{"authors"},
                "publisher",
                new String[]{"translators"},
                "9999",
                "8888",
                BooleanYn.Y,
                "category",
                "thunbnail",
                "barcode",
                "ebookBarcode",
                "status");
        bookRepository.save(book);
        List<Book> bookListAfterSaving = bookRepository.findAll();

        assertThat(size + 1, is(bookListAfterSaving.size()));
    }

    @Test
    public void 책정보수정(){
        Book book = bookRepository.findOne(1L);
        String[] authors = {"author1, author2, author3, author4"};
        book.setAuthors(authors);
        bookRepository.save(book);

        assertThat(authors, is(book.getAuthors()));
    }
}