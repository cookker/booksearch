package com.ms.ex.booksearch.booksearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.ex.booksearch.booksearch.common.UriFormat;
import com.ms.ex.booksearch.booksearch.dto.BookDto;
import com.ms.ex.booksearch.booksearch.dto.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
@Profile({"local", "test"})
public class BookApiControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void 책검색_없는데이터() throws Exception {
        //1.Given
        Map<String, String> request = new HashMap<>();
        request.put("title", "없는데이터");

        //When Then
        this.mockMvc.perform(get(getForUri(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void 책검색_정렬() throws Exception {
        //1.Given
        Map<String, String> request = new HashMap<>();
        request.put("title", "");
        request.put("sortName", "registerDatetime");
        request.put("sortOption", "asc");

        //When Then
        this.mockMvc.perform(get(getForUri(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void 책검색_전체() throws Exception {
        //1.Given
        Map<String, String> request = new HashMap<>();
        request.put("title", "");

        //When Then
        this.mockMvc.perform(get(getForUri(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(PageDto.PER_PAGE)));
    }

    private String getForUri(Map request) {
        String s = "/api/books?" + mapper.convertValue(request, UriFormat.class).toString();
        log.debug("{}", s);
        return s;
    }
}