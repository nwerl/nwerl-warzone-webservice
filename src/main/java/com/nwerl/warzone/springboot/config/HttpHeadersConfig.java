package com.nwerl.warzone.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class HttpHeadersConfig {
    private final CookieConfig cookieConfig;

    @Bean
    public HttpHeaders getHttpHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();

        headers.addAll(HttpHeaders.COOKIE,
                (List<String>)cookieConfig.getCookie().entrySet()
                .stream()
                .map(e -> new String(e.getKey()+"="+e.getValue()))
                .collect(Collectors.toList()));

        return headers;
    }
}
