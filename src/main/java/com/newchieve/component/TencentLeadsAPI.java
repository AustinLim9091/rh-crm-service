package com.newchieve.component;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * https://leads.qq.com/assets/doc/api_guide.pdf
 */
@FeignClient(value = "tencentApi" ,url ="${feign.client.tencent-api.url}")
public interface TencentLeadsAPI {

	@GetMapping(value = "/api/mv1/leads/list", headers = {"Content-Type=application/json", "X-Signature-Algorithm=${feign.client.tencent-api.sign-algorithm}"})
	ResponseEntity<String> getLeads(@RequestHeader("X-Signature") String signature,
									@RequestParam("start_time") String startTime,
									@RequestParam("end_time") String endTime,
									@RequestParam("page") int page,
									@RequestParam("page_size") int pageSize);

}
