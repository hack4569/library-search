package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.repository.KakaoBookRepository;
import com.library.repository.NaverBookRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookQueryService {
    private final NaverBookRepository naverBookRepository;

    private final KakaoBookRepository kakaoBookRepository;

    @CircuitBreaker(name = "naverSearch", fallbackMethod = "searchFallBack")
    public PageResult<SearchResponse> search(String query, int page, int size) {
        return naverBookRepository.search(query, page, size);
    }

    public PageResult<SearchResponse> searchFallBack(String query, int page, int size, Throwable throwable) {
        if (throwable instanceof CallNotPermittedException) { //서킷이 오픈되었을때 발생하는 에러
            return handleOpenCircuit(query, page, size);
        }
        return handleException(query, page, size, throwable);
    }

    private PageResult<SearchResponse> handleOpenCircuit(String query, int page, int size) {
        log.warn("[BookQueryService] Circuit Breaker is open! Fallback to kakao search.");
        return kakaoBookRepository.search(query, page, size);
    }

    private PageResult<SearchResponse> handleException(String query, int page, int size, Throwable throwable) {
        log.error("[BookQueryService] An error occurred! Fallback to kakao search. errorMessage={}", throwable.getMessage());
        return kakaoBookRepository.search(query, page, size);
    }
}
