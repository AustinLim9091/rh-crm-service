package com.newchieve.crm.scheduler;

import com.newchieve.crm.service.JinshujuService;
import com.newchieve.crm.service.TencentLeadsService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTask {

	@Autowired
	private JinshujuService jinshujuService;
	@Autowired
	private TencentLeadsService tencentLeadsService;

		@Scheduled(fixedRate = 600000)
	public void syncJsjData(){
		log.info("syncJsjData. started.");
		try {
			jinshujuService.syncData();
		} catch (Exception e) {
			log.info("syncJsjData. error. msg: {}, stacktrace: {}.", e.getMessage(), e.getStackTrace());
		}
	}

		@Scheduled(fixedRate = 600000)
	public void syncTencentLeadsData(){
		log.info("syncTencentLeadsData. started.");
		try {
			tencentLeadsService.syncData();
		} catch (Exception e) {
			log.info("syncTencentLeadsData. error. msg: {}, stacktrace: {}.", e.getMessage(), e.getStackTrace());
		}
	}
}
