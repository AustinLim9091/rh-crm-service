package com.newchieve.crm.service;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newchieve.component.JinshujuAPI;
import com.newchieve.crm.entity.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JinshujuService {

    @Autowired
    private JinshujuAPI jinshujuAPI;

    public void syncDataFromJsj() throws JsonProcessingException {
        String next = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = jinshujuAPI.pagedGetForms(next);
        Response r = objectMapper.readValue(response.getBody(), Response.class);

        while (CollUtil.isNotEmpty(r.getData())){

            next = r.getNext();
            response = jinshujuAPI.pagedGetForms(next);
            r = objectMapper.readValue(response.getBody(), Response.class);
        }
    }
}
