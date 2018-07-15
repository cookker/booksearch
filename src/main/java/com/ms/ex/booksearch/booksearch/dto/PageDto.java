 package com.ms.ex.booksearch.booksearch.dto;

 import lombok.Getter;
 import lombok.Setter;

 import java.util.List;

@Setter
@Getter
public class PageDto<T> {
    public static final int PER_PAGE = 2;

    private List<T> contents;
    private int currentPage;
    private int perPage = PER_PAGE;
    private long totalCount;

    public static <T>PageDto<T> of(List<T> contents, int currentPage, long totalCount){
        PageDto pageDto = new PageDto<T>();
        pageDto.setContents(contents);
        pageDto.setCurrentPage(currentPage);
        pageDto.setTotalCount(totalCount);

        return pageDto;
    }

}
