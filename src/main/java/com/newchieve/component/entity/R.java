package com.newchieve.component.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "全局响应体")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class R<T> {

//	@Schema(description = "调用id，用于调用跟踪")
//	private String traceId;

	@Schema(description = "响应数据，执行成功可能有值，执行失败时data为null")
	private T data;

	@Builder.Default
	@Schema(description = "执行结果，值为true和false，默认为true")
	private boolean success = true;

	@Schema(description = "错误信息列表，多个错误信息均会在列表中")
	private Set<String> errors;

	public R(T data){
		this.data = data;
	}

	public static R ok(){
		return R.builder()
				.build();
	}
	public static R ok(Object data){
		return R.builder()
				.data(data)
				.build();
	}

	public static R fail(){
		return R.builder()
				.success(false)
				.build();
	}

	public static R fail(Set<String> errors){
		return R.builder()
				.success(false)
				.errors(errors)
				.build();
	}

	public static R fail(String error){
		return R.builder()
				.success(false)
				.errors(Set.of(error))
				.build();
	}
}
