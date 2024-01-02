package com.newchieve.crm.service;

import com.newchieve.component.JinshujuAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class JinshujuService {

    @Autowired
    private JinshujuAPI jinshujuAPI;

    public String syncDataFromJsj(){
        String apiKey = "j67KM2MXZx8q5eWbQ0txGQ";
        String apiSecret = "4bkOVp3JE3S-VY2lDXGGkw";
        String credentials = apiKey + ":" + apiSecret;
        String base64EncodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        String token = "Basic " + base64EncodedCredentials;

        log.info("{}", token);
        ResponseEntity<String> response = jinshujuAPI.pagedGetForms();
        return response.getBody();
    }
}
