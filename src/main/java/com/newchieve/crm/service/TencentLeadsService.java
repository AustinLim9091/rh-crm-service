package com.newchieve.crm.service;

import java.util.Base64;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newchieve.component.TencentLeadsAPI;
import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.entity.dto.tencent.Leads;
import com.newchieve.crm.entity.dto.tencent.LeadsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TencentLeadsService {

    @Value("${feign.client.tencent-api.token}")
    private String token;
    @Value("${feign.client.tencent-api.secret}")
    private String secret;
    @Value("${feign.client.tencent-api.sign-algorithm}")
    private String signAlgorithm;
    @Autowired
    private TencentLeadsAPI tencentLeadsAPI;
    @Autowired
    private CustomerService customerService;

    public void syncData() throws JsonProcessingException {
        int page = 1,
                pageSize = 200;
        Long timestamp = System.currentTimeMillis() / 1000;
//        nonce不超过 32 个字符，由调⽤⽅⾃⾏⽣成，每个请求都不重复
        String nonce = IdUtil.simpleUUID(),
                signature = getSignature(signAlgorithm, token, timestamp, nonce, secret),
                start = DateUtil.formatDate(DateUtil.lastWeek()),
                end = DateUtil.today();
        ResponseEntity<String> r = tencentLeadsAPI.getLeads(signature, start, end, page, pageSize);
        ObjectMapper objectMapper = new ObjectMapper();
        LeadsResponse response = objectMapper.readValue(r.getBody(), LeadsResponse.class);

        log.info("resp: {}", response);

        while (response.getData() != null && CollUtil.isNotEmpty(response.getData().getList())){
            for (Leads l : response.getData().getList()) {
                Customer c = Customer.builder()
                        .name(l.getLeads_name())
                        .mobile(l.getLeads_tel())
                        .createTime(DateUtil.parseDate(l.getLeads_create_time()).toInstant().toEpochMilli() / 1000)
                        .extensionData(BeanUtil.beanToMap(l))
                        .build();
                customerService.saveIfNotExist(c);
            }

//            向后迭代
            if(page >= response.getData().getPageInfo().getTotalPage()){
                log.warn("No more data to sync. break.");
                break;
            }

            page ++;
            nonce = IdUtil.simpleUUID();
            signature = getSignature(signAlgorithm, token, timestamp, nonce, secret);
            r = tencentLeadsAPI.getLeads(signature, start, end, page, pageSize);
            response = objectMapper.readValue(r.getBody(), LeadsResponse.class);
        }
    }

    private String getSignature(String signAlgorithm, String token, Long timestamp, String nonce, String secret) {
        String seed;
        switch (signAlgorithm) {
        case "SHA1": {
            seed = DigestUtils.sha1Hex(token + "." + timestamp + "." + secret);
            break;
        }
        case "SHA256": {
            seed = DigestUtils.sha256Hex(token + "." + timestamp + "." + secret);
            break;
        }
        default: {
            throw new RuntimeException("Not supported signature algorithm: " + signAlgorithm);
        }
        }
        return Base64.getEncoder().encodeToString((token + "," + timestamp + "," + nonce + "," + seed).getBytes());
    }
}
