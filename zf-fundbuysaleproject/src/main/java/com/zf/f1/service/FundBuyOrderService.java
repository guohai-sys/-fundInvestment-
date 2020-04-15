package com.zf.f1.service;

import com.zf.c1.vo.JsonResult;

public interface FundBuyOrderService {
	/**
	 * id/基金代码/基金净值/净份额金额/买入费率 /买入费率金额
	 * 用于创建基金订单
	 * @param id
	 * @param fundCode
	 * @param netWorth
	 * @param shareMoney
	 * @param rate
	 * @param rateNum
	 * @return
	 */
	JsonResult buyFundDetail(String id, String fundCode,String fundFullName,Double netWorth, Double shareMoney, Double rate,
			Double rateNum,Double mny);

	/**
	 * 根据id得到该用户的所有的基金订单
	 * @param id
	 * @return
	 */
	JsonResult getFundOrder(String id);
	/**
	 * 基金赎回
	 * @param id
	 * @param orderId 
	 * @return
	 */

	JsonResult opretionSaleDetailFromService(String id, String code, Integer orderId);
}
