package com.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "naverClient", url = "${external.naver.url}", configuration = NaverClientConfiguraion.class)
public interface NaverClient {
    @GetMapping("/v1/search/book.json")
    String search(SearchParam searchParam);
}
