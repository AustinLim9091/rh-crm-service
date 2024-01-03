package com.newchieve.crm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newchieve.crm.entity.Customer;
import com.newchieve.crm.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {

}
