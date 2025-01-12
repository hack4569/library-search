package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookApplicationService {
    private final BookQueryService bookQueryService;

    public PageResult<SearchResponse> search(String query, int page, int size) {
        PageResult<SearchResponse> response = bookQueryService.search(query, page, size);
        return response;
    }
}
