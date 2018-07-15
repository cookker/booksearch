package com.ms.ex.booksearch.booksearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.ex.booksearch.booksearch.common.error.ApiException;
import com.ms.ex.booksearch.booksearch.common.error.SystemErr;
import com.ms.ex.booksearch.booksearch.dto.BookDto;
import com.ms.ex.booksearch.booksearch.dto.BookSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@Slf4j
@Service
public class BookSearchApiServiceImpl implements BookSearchApiService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${book.api.url}")
    private String bookApiHost;
    @Value("${book.api.auth}")
    private String bookApiAuth;
    @Value("${book.api.app-key}")
    private String bookApiAppKey;

    /**
     * 실제 API호출 시 제대로 된 응답이 오지 않아 해당 api를 사용하지 않고, 스펙만 맞춰놓았습니다.
     *
     * @param request
     * @return
     */
    public BookDto getBookList(BookSearchRequest request){
        String uri = UriComponentsBuilder.fromHttpUrl(bookApiHost)
                .path("/search/book?query={query}&target={target}&page={page}&size={size}&category={category}")
                .buildAndExpand(request.getQuery(),
                        request.getTarget(),
                        request.getPage(),
                        request.getSize(),
                        request.getCategory())
                .toUriString();

        restTemplate
                .exchange(uri, HttpMethod.GET, setParameter(""), BookDto.class)
                .getBody();

        return null;
    }

    private HttpEntity setParameter(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Authorization", bookApiAuth + " " + bookApiAppKey);

        try {
            return new HttpEntity<>(new ObjectMapper().writeValueAsString(body), headers);
        } catch (JsonProcessingException e) {
            throw new ApiException(SystemErr.RETRY_REQUEST);
        }
    }
}
