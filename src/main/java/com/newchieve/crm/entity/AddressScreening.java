package com.newchieve.crm.entity;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * kyc address-screening record
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressScreening {

	private String email;

	private String asset;

	private String address;

	private String level;

	@Builder.Default
	private Map<String, Object> details = new HashMap<>();
	
}
