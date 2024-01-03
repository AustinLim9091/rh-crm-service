package com.newchieve.component.util;

import java.util.Base64;

public class JinshujuTokenUtil {
	public static String getToken(){
		String apiKey = "j67KM2MXZx8q5eWbQ0txGQ";
		String apiSecret = "4bkOVp3JE3S-VY2lDXGGkw";
		String credentials = apiKey + ":" + apiSecret;
		String base64EncodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		return "Basic " + base64EncodedCredentials;
	}
}
