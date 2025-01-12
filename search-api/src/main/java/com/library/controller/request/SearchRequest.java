package com.library.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchRequest {
    private String query;
    private Integer page;
    private int size;

}
