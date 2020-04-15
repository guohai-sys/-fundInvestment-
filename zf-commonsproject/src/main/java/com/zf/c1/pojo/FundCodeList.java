package com.zf.c1.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("fund_code_list")
public class FundCodeList extends BasePojo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5029003884239955613L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String fundCode;
	private Double netWorth;
}
