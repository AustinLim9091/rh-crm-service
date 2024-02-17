package com.newchieve.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.newchieve.component.TencentLeadsAPI;
import com.newchieve.component.entity.R;
import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.service.CustomerService;
import com.newchieve.crm.service.JinshujuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Slf4j
@Tag(name = "[PUBLIC] Address Screening")
@RestController
@RequestMapping("/crm")
public class CustomerController {

	@Autowired
	private JinshujuService jinshujuService;
    @Autowired
    private CustomerService customerService;

	@Operation(summary = "新建")
	@GetMapping
	public ResponseEntity<R> get() throws JsonProcessingException {
		jinshujuService.syncDataFromJsj();
		return ResponseEntity.ok(R.ok());
	}
// {"name":"陈女士 日本 ","handleStatus": 0, "mobile":"18669996771", "adviserStaffId": 354, "adviserStaffName": "金舒"}
    @PostMapping("/customer/import")
    public void batchImport(@RequestBody List<Customer> customers){
        for(Customer x : customers){
            Long count = customerService.lambdaQuery()
                    .eq(Customer::getMobile, x.getMobile())
                    .count();
            if(count > 0){
                log.info("batchImport. exist: {}. skip.", x.getMobile());
                continue;
            }

            x.setRemark("CRM导入");
            x.setStatus(20);
            if(x.getHandleStatus() == 1) {
                x.setHandleStatus(20);
            }
            x.setCreateTime(1706970477L);

            customerService.save(x);
        }
    }

    @Value("${feign.client.tencent-api.token}")
    private String token;
    @Value("${feign.client.tencent-api.secret}")
    private String secret;
    @Value("${feign.client.tencent-api.sign-algorithm}")
    private String signAlgorithm;
    @Autowired
    private TencentLeadsAPI tencentLeadsAPI;

    @GetMapping("/test")
    public void test(){
        String nonce = "2"; // 不超过 32 个字符，由调⽤⽅⾃⾏⽣成，每个请求都不重复
        Long timestamp = System.currentTimeMillis() / 1000;
        String signature = getSignature(signAlgorithm, token, timestamp, nonce, secret);
        String start = "2023-12-25",
                end = "2023-12-31";
        ResponseEntity<String> resp = tencentLeadsAPI.getLeads(start, end, signature);
        String body = resp.getBody();
        log.info("test. body: {}", body);
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
