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
@TableName("order_fallback_log")
public class OrderFallbackLogPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 693504915600530751L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Double netWorth;//赎回净值
	private Double share;//份额
	private Double redeemSum;//赎回总金额
	private Double redeemRate;//赎回费率
	private Double redeemServiceCharge;//赎回手续费
	private Double netAmount;//赎回净额
	private Double mnyMoney;//总花费
	private Double netEarning;//净收益
	private Date createTimeDate;//操作时间
}
