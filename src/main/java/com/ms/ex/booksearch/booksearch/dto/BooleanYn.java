package com.ms.ex.booksearch.booksearch.dto;

import lombok.Getter;

@Getter
public enum BooleanYn {
    Y("Y"),
    N("N"),
    ;

    private String value;

    BooleanYn(String value) {
        this.value = value;
    }

    public static BooleanYn of(String Yn){
        return "Y".equalsIgnoreCase(Yn) ? BooleanYn.Y : BooleanYn.N;
    }

    public static BooleanYn of(boolean Yn){
        return Yn ? BooleanYn.Y : BooleanYn.N;
    }
}
