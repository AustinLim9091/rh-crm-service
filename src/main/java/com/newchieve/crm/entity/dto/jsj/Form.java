package com.newchieve.crm.entity.dto.jsj;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 表单数据
 * https://open.jinshuju.net/#/api_v1/endpoints/get_forms
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Form {

	private String name,
			token,
			description;
	private List<Map<String, Object>> fields;

}