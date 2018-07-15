package com.ms.ex.booksearch.booksearch.common;

import com.ms.ex.booksearch.booksearch.common.util.SplitUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class SplitUtilsTest {

    @Test
    public void 콤마구분자로_나뉘어지는지확인_1개만있을경우(){
        assertEquals(1, SplitUtils.split("홍길동").length);
    }

    @Test
    public void 콤마구분자로_나뉘어지는지확인_2개있을경우(){
        assertEquals(2, SplitUtils.split("홍길동, 이순신").length);
    }

    @Test
    public void 콤마구분자로_나뉘어지는지확인_빈값포함(){
        assertEquals(2, SplitUtils.split("홍길동, ,, 고길동,").length);
    }

    @Test
    public void 콤마구분자로_나뉘어지는지확인_빈값만있는경우(){
        assertEquals(1, SplitUtils.split("").length);
    }

}