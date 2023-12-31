package com.newchieve.component;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 */
@FeignClient(value = "jinshujuAPI" ,url ="https://jinshuju.net")
public interface JinshujuAPI {

	@GetMapping(value = "/api/v1/forms", headers = {"Content-Type=application/json", "Accept=application/json", "Authorization={token}"})
	ResponseEntity<String> pagedGetForms(@Param ("token") String token);

}
