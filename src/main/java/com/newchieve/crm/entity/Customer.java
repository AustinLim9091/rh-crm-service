package com.newchieve.crm.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
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
@TableName(value = "rd_clue", autoResultMap = true)
public class Customer {
	@TableId(type = IdType.AUTO)
	private Integer id;

	private String name,
			mobile,
			remark,
            adviserStaffName,
			projectName;

	private Integer status,
            handleStatus,
            adviserStaffId,
			projectId,
            source;

	private Long createTime;

	@TableField(typeHandler = JacksonTypeHandler.class)
	private Map<String, Object> extensionData;

}