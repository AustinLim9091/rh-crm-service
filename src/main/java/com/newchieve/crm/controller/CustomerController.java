package com.newchieve.crm.controller;

import java.util.List;

import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.service.CustomerService;
import com.newchieve.crm.service.JinshujuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "[PUBLIC] Address Screening")
@RestController
@RequestMapping("/crm")
public class CustomerController {

	@Autowired
	private JinshujuService jinshujuService;
    @Autowired
    private CustomerService customerService;

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

}
