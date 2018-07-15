package com.ms.ex.booksearch.booksearch.repository;

import com.ms.ex.booksearch.booksearch.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByTitleContaining(@Param("title") String title, Pageable pageRequest);
    List<Book> findAllByTitle(@Param("title") String title, Pageable pageRequest);
}
