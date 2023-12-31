package com.newchieve.component.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页请求")
public class PageReq{

	@Min(1)
	@Max(1000)
	@Schema(description = "每页记录条数，默认10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long size = 10;

	@Min(1)
	@Max(10000)
	@Schema(description = "当前页，默认第一页：1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long current = 1;

	@Schema(description = "关键词")
	private String keyword;

	@Schema(description = "状态数组，实现多status值的查询")
	private List<Integer> statusIn;

	@Schema(description = "记录创建开始时间")
	private LocalDate createTimeStart;

	@Schema(description = "记录创建截止时间")
	private LocalDate createTimeEnd;

	@Schema(description = "最后修改开始时间")
	private LocalDate updateTimeStart;

	@Schema(description = "最后修改截止时间")
	private LocalDate updateTimeEnd;

	@Schema(description = "记录创建开始日期")
	private LocalDate createDateStart;

	@Schema(description = "记录创建截止日期")
	private LocalDate createDateEnd;

	@Schema(description = "最后修改开始日期")
	private LocalDate updateDateStart;

	@Schema(description = "最后修改截止日期")
	private LocalDate updateDateEnd;

	@Schema(description = "操作人")
	private Long createBy;

	@Schema(description = "最后修改人")
	private Long updateBy;

	public LocalDate getCreateTimeEnd() {
		if(createTimeEnd == null){
			return null;
		}
		return createTimeEnd.plusDays(1);
	}

	public LocalDate getUpdateTimeEnd() {
		if(updateTimeEnd == null){
			return null;
		}
		return updateTimeEnd.plusDays(1);
	}

	public LocalDate getCreateDateEnd() {
		if(createDateEnd == null){
			return null;
		}
		return createDateEnd.plusDays(1);
	}

	public LocalDate getUpdateDateEnd() {
		if(updateDateEnd == null){
			return null;
		}
		return updateDateEnd.plusDays(1);
	}
}
