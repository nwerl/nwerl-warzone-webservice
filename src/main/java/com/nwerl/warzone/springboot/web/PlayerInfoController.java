package com.nwerl.warzone.springboot.web;

import com.nwerl.warzone.springboot.service.PlayerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@RestController
public class PlayerInfoController {
    //private final AuthTokenService authTokenService;
    private final PlayerInfoService playerInfoService;

    @GetMapping("/getPlayerInfo")
    public LinkedHashMap getToken() throws IOException {
        return playerInfoService.getPlayerInfo();
    }
}
