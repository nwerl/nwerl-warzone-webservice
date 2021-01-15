package com.nwerl.warzone.springboot.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class HttpHeadersConfig {
    @Value("${activision.username}")
    private String username;
    @Value("${activision.password}")
    private String password;

    private Connection.Response authToken() throws IOException {
        Map<String, String> loginCookies = Jsoup.connect("https://profile.callofduty.com/cod/login").execute().cookies();

        return Jsoup.connect("https://profile.callofduty.com/do_login?new_SiteId=cod")
                .cookies(loginCookies)
                .data("username", username)
                .data("password", password)
                .data("remember_me", "true")
                .data("_csrf", loginCookies.get("XSRF-TOKEN"))
                .method(Connection.Method.POST)
                .userAgent("PostmanRuntime/7.26.8")
                .execute();
    }

    private Map<String, String> getCookie() throws IOException {
        return authToken().cookies();
    }

    @Bean
    public HttpHeaders getHttpHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();

        headers.addAll(HttpHeaders.COOKIE,
                (List<String>)getCookie().entrySet()
                .stream()
                .map(e -> new String(e.getKey()+"="+e.getValue()))
                .collect(Collectors.toList()));

        return headers;
    }
}
