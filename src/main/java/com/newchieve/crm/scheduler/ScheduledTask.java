package com.newchieve.crm.scheduler;

import com.newchieve.crm.service.JinshujuService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTask {

	@Autowired
	private JinshujuService jinshujuService;

	@Scheduled(fixedRate = 600000)
	public void syncData(){
		log.info("syncData. started.");
		try {
			jinshujuService.syncDataFromJsj();
		} catch (Exception e) {
			log.info("syncData. error. msg: {}, stacktrace: {}.", e.getMessage(), e.getStackTrace());
		}
	}
}
