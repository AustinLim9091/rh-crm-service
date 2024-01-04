package com.newchieve.crm.service;

import java.time.ZonedDateTime;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newchieve.component.JinshujuAPI;
import com.newchieve.crm.config.AppConfig;
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
    private AppConfig appConfig;
    @Autowired
    private JinshujuAPI jinshujuAPI;
    @Autowired
    private CustomerService customerService;

    public void syncDataFromJsj() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
//        获取首页表单列表
        ResponseEntity<String> r = jinshujuAPI.getFormList(null);
        FormListResponse formList = objectMapper.readValue(r.getBody(), FormListResponse.class);

        String next;
        while (CollUtil.isNotEmpty(formList.getData())){
            for (Form f : formList.getData()) {
                try {
//                    获取表单格式
                    r = jinshujuAPI.getForm(f.getToken());
                    FormResponse fr = objectMapper.readValue(r.getBody(), FormResponse.class);

                    String keyName = null, keyMobile = null;
                    for (Map<String, Object> m : fr.getFields()) {
                        for (Map.Entry<String, Object> entry : m.entrySet()) {
                            Map<String, Object> t = (Map<String, Object>) m.get(entry.getKey());
                            if (appConfig.getJsjFormLabelsForMobile().contains((String) t.get("label"))) {
                                keyMobile = entry.getKey();
                            } else if(appConfig.getJsjFormLabelsForName().contains((String) t.get("label"))){
                                keyName = entry.getKey();
                            }
                        }
                    }

//                    获取表单数据
                    r = jinshujuAPI.getFormData(f.getToken());
                    FormDataResponse d = objectMapper.readValue(r.getBody(), FormDataResponse.class);
                    for (Map<String, Object> m : d.getData()) {
                        String name = (String) m.get(keyName),
                                mobile = (String) m.get(keyMobile);
                        if(StrUtil.isBlank(name) || StrUtil.isBlank(mobile)) {
                            log.warn("syncDataFromJsj. customer not valid. continue. form.token: {}, form.name: {}, data: {}", f.getToken(), f.getName(), m);
                            continue;
                        }

                        Customer c = Customer.builder()
                                .name((String) m.get(keyName))
                                .mobile((String) m.get(keyMobile))
                                .createTime(ZonedDateTime.parse(m.get("updated_at").toString()).toInstant().toEpochMilli() / 1000)
                                .remark("jsj_" + f.getName())
                                .build();
//                        客户信息入库
                        customerService.save(c);
                    }
                } catch (Exception e){
                    log.error("syncDataFromJsj. form: {}, msg: {}, stacktrace: {}", f.getToken(), e.getMessage(), e.getStackTrace());
                }
            }

//            向后迭代
            next = formList.getNext();
            if(StrUtil.isBlank(next)){
                log.warn("No more forms to sync. break.");
                break;
            }
            r = jinshujuAPI.getFormList(next);
            formList = objectMapper.readValue(r.getBody(), FormListResponse.class);
        }
    }
}
