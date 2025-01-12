package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.repository.NaverBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookQueryService {
    private final NaverBookRepository naverBookRepository;
    public PageResult<SearchResponse> search(String query,int page,int size) {
        return naverBookRepository.search(query, page, size);
    }
}
