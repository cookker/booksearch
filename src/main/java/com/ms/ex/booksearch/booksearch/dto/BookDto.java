package com.ms.ex.booksearch.booksearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private String title;
    private String contents;
    private String url;
    private String isbn;
    private String datetime;
    private String authors;
    private String publisher;
    private String translators;
    private String price;
    private String salePrice;
    private String saleYn;
    private String category;
    private String thumbnail;
    private String barcode;
    private String ebookBarcode;
    private String status;
    private String bookmarked;
}
