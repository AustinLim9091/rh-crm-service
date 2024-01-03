package com.newchieve.component;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
@FeignClient(value = "jinshujuApi" ,url ="${feign.client.jinshuju-api.url}")
public interface JinshujuAPI {

	@GetMapping(value = "/api/v1/forms", headers = {"Content-Type=application/json", "Accept=application/json", "Authorization=${feign.client.jinshuju-api.token}"})
	ResponseEntity<String> getFormList(@RequestParam("next") String next);
	@GetMapping(value = "/api/v1/forms/{token}", headers = {"Content-Type=application/json", "Accept=application/json", "Authorization=${feign.client.jinshuju-api.token}"})
	ResponseEntity<String> getForm(@PathVariable("token") String token);
	@GetMapping(value = "/api/v1/forms/{token}/entries", headers = {"Content-Type=application/json", "Accept=application/json", "Authorization=${feign.client.jinshuju-api.token}"})
	ResponseEntity<String> getFormData(@PathVariable("token") String token);

}
