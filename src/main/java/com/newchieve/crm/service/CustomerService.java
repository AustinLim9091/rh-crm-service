package com.newchieve.crm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {

	public boolean saveIfNotExist(Customer c){
		boolean exists = this.exists(c.getMobile());
		if(exists){
			return false;
		}

		return this.save(c);
	}

	public boolean exists(String mobile){
		Long count = this.lambdaQuery()
				.eq(Customer::getMobile, mobile)
				.count();
		if(count > 0){
			log.warn("exists. customer exist. mobile: {}", mobile);
			return true;
		}
		return false;
	}

}
