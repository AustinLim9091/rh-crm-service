package com.newchieve.component.exception;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String methodKey, Response response) {
		String message = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = objectMapper.readValue(response.body().asInputStream(), Map.class);
			if (HttpStatus.BAD_REQUEST.value() == response.status()) {
				String msg = (String) map.get("message");
				if(StrUtil.isBlank(msg)){
					msg = (String) map.get("msg");
				}
				return new BusinessException(HttpStatus.BAD_REQUEST, msg);
			} else {
				log.error("decode. response.status: {}, response.body: {}", response.status(), map);
			}
		} catch (Exception ignoreException) {
			log.error("decode. msg: {}, stacktrace: {}", ignoreException.getMessage(), ignoreException.getStackTrace());
		}

		return new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Execute failed. Please retry later.");
	}
}
