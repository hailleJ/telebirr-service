package io.alet.telebirr_request.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telebirr/callback/charging")
@RequiredArgsConstructor
@Slf4j
public class ChargingCallbackController {



    @RequestMapping
    public void callback(@RequestBody String body) {

        log.info("charging callback::{}", body);

    }
}
