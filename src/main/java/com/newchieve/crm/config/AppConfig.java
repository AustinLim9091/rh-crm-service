package com.newchieve.crm.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {

	private List<String>
			jsjFormLabelsForName,
			jsjFormLabelsForMobile;

}
