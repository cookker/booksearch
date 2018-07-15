package com.ms.ex.booksearch.booksearch.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@AllArgsConstructor
public class BookWebController {

    @GetMapping("/bookform")
    public String bookForm(){
        return "bookform";
    }

    @GetMapping("/books/{id:[0-9]+}")
    public String getBook(@PathVariable("id") Long id){
        return "/bookdetail";
    }
}
