package com.newchieve.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("rd_clue")
public class Customer {
	@TableId(type = IdType.AUTO)
	private Integer id;

	private String name,
			mobile,
			remark,
            adviserStaffName;

	private Integer status,
            handleStatus,
            adviserStaffId;

	private Long createTime;

}