package com.zf.c1.pojo;

import java.io.Serializable;
import java.util.Date;

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
@TableName("fund_detail")
public class FundDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7500141595378642780L;
	
	@TableId
	private String fundCode;;//int(255)基金代码
	private String fundFullName;//varchar(255)基金全称
	private String fundKind;//基金类型
	private Date establishDate;//datetime成立日期
	private Double assertSize;//decimal(10,0)资产规模
	private String fundManager;//varchar(65)基金管理公司
	private String fundTrustee;//varchar(65)基金托管人
	private Double netWorth;//净值
	private Double sameKindNetWorth;//同类净值
	private Double huShen;//沪深
}
