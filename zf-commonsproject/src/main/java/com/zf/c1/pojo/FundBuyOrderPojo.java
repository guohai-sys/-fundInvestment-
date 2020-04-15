package com.zf.c1.pojo;

import java.io.Serializable;
import java.util.Date;

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
@TableName("fund_buy_order")
public class FundBuyOrderPojo extends BasePojo implements Serializable{
	
	private static final long serialVersionUID = 3073186260577133225L;
	
	@TableId(type = IdType.AUTO)
	private Integer orderId;//订单id
	private Integer status;//订单状态0为未卖出，1为已卖出
	private String id;//'用户id',
	private String fundCode;// '基金代码',
	private String fundFullName;//基金全称
	private Double netWorth;// '基金购买时的净值',
	private Double shareMoney;// '净申购金额',
	private Double share;// '净份额',
	private Double rate;// '买入费率',
	private Double rateNum;// '买入费率金额',
	private Double mnyMoney;// '全部金额',
	private Date saleDate;//卖出日期
	
}
