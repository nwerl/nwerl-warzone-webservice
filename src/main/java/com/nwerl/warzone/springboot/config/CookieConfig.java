package com.nwerl.warzone.springboot.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class CookieConfig {
    @Value("${activision.username}")
    String username;
    @Value("${activision.password}")
    String password;

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
    @Bean
    public Map<String, String> getCookie() throws IOException {
        return authToken().cookies();
    }
}