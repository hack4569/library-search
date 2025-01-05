package com.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = NaverClientTest.TestConfig.class)//RestClient 를 주입받아서 사용해야 되기 때문에 사용함.
@ActiveProfiles("test")
class NaverClientTest {

    @ComponentScan(basePackageClasses = NaverClient.class)
    static class TestConfig {}

    @Autowired
    NaverClient naverClient;

    @Test
    void callNaver() {
        String http = naverClient.search("HTTP");
        System.out.println(http);
    }
}