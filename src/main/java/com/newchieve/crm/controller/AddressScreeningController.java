package com.newchieve.crm.controller;

import com.newchieve.component.entity.R;
import com.newchieve.crm.service.JinshujuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "[PUBLIC] Address Screening")
@RestController
@RequestMapping("/crm")
public class AddressScreeningController {

	@Autowired
	private JinshujuService jinshujuService;

	@Operation(summary = "新建")
	@GetMapping
	public ResponseEntity<R<String>> get() {
        String result = jinshujuService.syncDataFromJsj();
        return ResponseEntity.ok(R.ok(result));
	}

}
