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
@TableName("fund_code_same_hu")
public class FundCodeSameHu extends BasePojo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6957857031967018462L;
	@TableId(type = IdType.AUTO)
	private Integer id;//'同类净值和沪深300id',
	private  String fundCode ;// '基金代码',
	private  Double sameKindNetWorth;// '同类均值',
	private  Double huShen ;// '沪深300',
}
