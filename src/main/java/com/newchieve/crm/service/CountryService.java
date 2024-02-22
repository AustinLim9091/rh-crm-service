package com.newchieve.crm.service;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newchieve.crm.entity.Country;
import com.newchieve.crm.mapper.CountryMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CountryService extends ServiceImpl<CountryMapper, Country> {

	public Country findByName(String title) {
		List<Country> list = this.lambdaQuery().eq(Country::getTitle, title).list();
		if(CollUtil.isNotEmpty(list)){
			return list.get(0);
		}

		return null;
	}
}
