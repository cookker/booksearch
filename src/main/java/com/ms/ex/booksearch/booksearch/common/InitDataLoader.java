package com.ms.ex.booksearch.booksearch.common;

import com.ms.ex.booksearch.booksearch.dto.BooleanYn;
import com.ms.ex.booksearch.booksearch.entity.Book;
import com.ms.ex.booksearch.booksearch.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Profile({"local", "test"})
public class InitDataLoader {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init(){
        Book book1 = new Book(
                "미움받을 용기",
                "다음 웹툰 X 《미움받을 용기》 스페셜 리커버 에디션!  청춘들이 가장 사랑하는 웹툰작가 만물상과 S_owl, 김경, 이은재가 선보이는 《미움받을 용기》 스페셜 리커버 에디션이 출간되었다...",
                "http://book.daum.net/detail/book.do?bookid=KOR9788996991342",
                "8996991341 9788996991342",
                "2014-11-17T00:00:00.000+09:00",
                new String []{"기시미 이치로", "고가 후미타케"},
                "인플루엔셜",
                new String[]{"전경아"},
                "14900",
                "13410",
                BooleanYn.Y,
                "인문",
                "http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788996991342%3Fmoddttm=20170620104117",
                "KOR9788996991342",
                "DGT4808996991342",
                "정상판매");

        bookRepository.save(book1);

        Book book2 = new Book(
                "역사의 역사",
                "시대를 읽는 작가 유시민, ‘역사란 무엇인가’를 묻다! ",
                "http://book.daum.net/detail/book.do?bookid=KOR9788971998557",
                "8971998555 8971998555",
                "2018-06-25T00:00:00.000+09:00",
                new String[]{"유시민"},
                "돌베개",
                new String[]{""},
                "16000",
                "14400",
                BooleanYn.Y,
                "역사/문화",
                "http://t1.daumcdn.net/thumb/R155x225/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2Fimage%2FKOR9788971998557%3Fmoddttm=20180712091005",
                "",
                "",
                "정상판매");

        bookRepository.save(book2);

        Book book3 = new Book(
                "미움받을 용기2",
                "다음 웹툰 X 《미움받을 용기》 스페셜 리커버 에디션!  청춘들이 가장 사랑하는 웹툰작가 만물상과 S_owl, 김경, 이은재가 선보이는 《미움받을 용기》 스페셜 리커버 에디션이 출간되었다...",
                "http://book.daum.net/detail/book.do?bookid=KOR9788996991342",
                "8996991341 9788996991342",
                "2014-11-17T00:00:00.000+09:00",
                new String []{"기시미 이치로", "고가 후미타케"},
                "인플루엔셜",
                new String[]{"전경아"},
                "14900",
                "13410",
                BooleanYn.Y,
                "인문",
                "http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788996991342%3Fmoddttm=20170620104117",
                "KOR9788996991342",
                "DGT4808996991342",
                "정상판매");

        bookRepository.save(book3);

        Book book4 = new Book(
                "역사의 역사2",
                "시대를 읽는 작가 유시민, ‘역사란 무엇인가’를 묻다! ",
                "http://book.daum.net/detail/book.do?bookid=KOR9788971998557",
                "8971998555 8971998555",
                "2018-06-25T00:00:00.000+09:00",
                new String[]{"유시민"},
                "돌베개",
                new String[]{""},
                "16000",
                "14400",
                BooleanYn.Y,
                "역사/문화",
                "http://t1.daumcdn.net/thumb/R155x225/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2Fimage%2FKOR9788971998557%3Fmoddttm=20180712091005",
                "",
                "",
                "정상판매");

        bookRepository.save(book4);

    }
}
