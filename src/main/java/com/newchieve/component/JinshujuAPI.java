package com.newchieve.component;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
@FeignClient(value = "jinshujuApi" ,url ="${feign.client.jinshuju-api.url}")
public interface JinshujuAPI {

	@GetMapping(value = "/api/v1/forms", headers = {"Content-Type=application/json", "Accept=application/json", "Authorization=${feign.client.jinshuju-api.token}"})
	ResponseEntity<String> pagedGetForms(@RequestParam("next") String next);

}
