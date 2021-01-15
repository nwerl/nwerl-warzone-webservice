package com.nwerl.warzone.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Service
public class PlayerInfoService {
    private final HttpHeaders headers;
    private final ApiService<LinkedHashMap> apiService;

    public LinkedHashMap getPlayerInfo() {
        String url = "https://my.callofduty.com/api/papi-client/crm/cod/v2/title/mw/platform/battle/gamer/iShot%2321899/matches/mp/start/0/end/0/details";
        System.out.println(headers.values());
        return apiService.get(url, headers).getBody();
    }
}