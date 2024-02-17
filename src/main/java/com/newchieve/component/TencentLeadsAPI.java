package com.newchieve.component;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
@FeignClient(value = "tencentApi" ,url ="${feign.client.tencent-api.url}")
public interface TencentLeadsAPI {

	@GetMapping(value = "/api/mv1/leads/list", headers = {
            "Content-Type=application/json",
//            "={#signature}",
            "X-Signature-Algorithm=${feign.client.tencent-api.sign-algorithm}"})
	ResponseEntity<String> getLeads(@RequestParam("start_time") String startTime,
                                    @RequestParam("end_time") String endTime,
                                    @RequestHeader("X-Signature") String signature);

}
