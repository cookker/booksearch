package com.ms.ex.booksearch.booksearch.entity;

import com.ms.ex.booksearch.booksearch.dto.BooleanYn;
import com.ms.ex.booksearch.booksearch.common.util.SplitUtils;
import com.ms.ex.booksearch.booksearch.dto.BookDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book",
        indexes = {
                @Index(name = "idx_title", columnList = "title", unique = false),
                @Index(name = "idx_isbn", columnList = "isbn", unique = false),
                @Index(name = "idx_authors", columnList = "authors", unique = false)
        }
)
@NoArgsConstructor
public class Book extends AbstractTimestamp{
    @Id
    @GeneratedValue
    private Long id;

    //도서 제목
    @Column(name = "title", nullable = false)
    private String title;
    //도서 소개
    private String contents;
    //다음 책 링크
    private String url;
    //ISBN 번호. 국제 표준 도서번호(ISBN10	ISBN13) (ISBN10 또는 ISBN13 중에 하나 이상 존재하면 ' '(공백)을 구분자로 출렴됨)
    private String isbn;
    //도서 출판날짜. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    @Column(name = "datetime", nullable = false)
    private String datetime;
    //도서 저자 리스트	Array of String
    @Column(name = "authors", nullable = false)
    @Convert(converter = ArrayConvertor.class)
    private String [] authors;
    //출판사
    private String publisher;
    //번역자 리스트
    @Column(name = "translators")
    @Convert(converter = ArrayConvertor.class)
    private String [] translators;
    //도서 정가
    @Column(name = "price", nullable = false)
    private String price = "0";
    //도서 판매가
    private String salePrice;
    //도서판매여부
    @Enumerated(value = EnumType.STRING)
    private BooleanYn saleYn = BooleanYn.Y;
    //도서 카테고리 정보
    private String category;
    //도서 표지 썸네일
    private String thumbnail;
    //교보문고 바코드 정보
    private String barcode;
    //교보문고 전자책 바코드 정보
    private String ebookBarcode;
    //도서 상태 정보(정상, 품절, 절판 등)
    private String status;
    @Enumerated(value = EnumType.STRING)
    private BooleanYn bookmarked = BooleanYn.N;

    public Book(String title,
                String contents,
                String url,
                String isbn,
                String datetime,
                String [] authors,
                String publisher,
                String [] translators,
                String price,
                String salePrice,
                BooleanYn saleYn,
                String category,
                String thumbnail,
                String barcode,
                String ebookBarcode,
                String status) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.isbn = isbn;
        this.datetime = datetime;
        this.authors = authors;
        this.publisher = publisher;
        this.translators = translators;
        this.price = price;
        this.salePrice = salePrice;
        this.saleYn = saleYn;
        this.category = category;
        this.thumbnail = thumbnail;
        this.barcode = barcode;
        this.ebookBarcode = ebookBarcode;
        this.status = status;
    }

    public Book(BookDto bookDto){
        this.title = bookDto.getTitle();
        this.contents = bookDto.getContents();
        this.url = bookDto.getUrl();
        this.isbn = bookDto.getIsbn();
        this.datetime = bookDto.getDatetime();
        this.authors = SplitUtils.split(bookDto.getAuthors());
        this.publisher = bookDto.getPublisher();
        this.translators = SplitUtils.split(bookDto.getTranslators());
        this.price = bookDto.getPrice();
        this.salePrice = bookDto.getSalePrice();
        this.saleYn = BooleanYn.of(bookDto.getSaleYn());
        this.category = bookDto.getCategory();
        this.thumbnail = bookDto.getThumbnail();
        this.barcode = bookDto.getBarcode();
        this.ebookBarcode = bookDto.getEbookBarcode();
        this.status = bookDto.getStatus();
    }
}
