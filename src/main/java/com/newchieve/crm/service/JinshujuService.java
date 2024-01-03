package com.newchieve.crm.service;

import java.time.ZonedDateTime;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newchieve.component.JinshujuAPI;
import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.entity.dto.Form;
import com.newchieve.crm.entity.dto.FormDataResponse;
import com.newchieve.crm.entity.dto.FormListResponse;
import com.newchieve.crm.entity.dto.FormResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JinshujuService {

    @Autowired
    private JinshujuAPI jinshujuAPI;
    @Autowired
    private CustomerService customerService;

    public void syncDataFromJsj() throws JsonProcessingException {
        String next = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = jinshujuAPI.getFormList(next);
        FormListResponse r = objectMapper.readValue(response.getBody(), FormListResponse.class);

        while (CollUtil.isNotEmpty(r.getData())){
            for (Form f : r.getData()) {
                if(!"CXhfz1".equals(f.getToken())){
                    continue;
                }
                response = jinshujuAPI.getForm(f.getToken());
                FormResponse fr = objectMapper.readValue(response.getBody(), FormResponse.class);

                String keyName = null, keyMobile = null;
                for (Map<String, Object> m : fr.getFields()) {
                    for (Map.Entry<String, Object> entry : m.entrySet()) {
                        Map<String, Object> t = (Map<String, Object>) m.get(entry.getKey());
                        if ("手机号码".equals(t.get("label"))) {
                            keyMobile = entry.getKey();
                        } else if("姓名".equals(t.get("label"))){
                            keyName = entry.getKey();
                        }
                    }
                }

                response = jinshujuAPI.getFormData(f.getToken());
                FormDataResponse d = objectMapper.readValue(response.getBody(), FormDataResponse.class);
                for (Map<String, Object> m : d.getData()) {
                    Customer c = Customer.builder()
                            .name((String) m.get(keyName))
                            .mobile((String) m.get(keyMobile))
                            .createTime(ZonedDateTime.parse(m.get("updated_at").toString()).toInstant().toEpochMilli() / 1000)
                            .remark("jsj_" + f.getName())
                            .build();
                    customerService.save(c);
                }
            }

            next = r.getNext();
            if(StrUtil.isBlank(next)){
                log.warn("No more forms to sync. break.");
                break;
            }
            response = jinshujuAPI.getFormList(next);
            r = objectMapper.readValue(response.getBody(), FormListResponse.class);
        }
    }
}
