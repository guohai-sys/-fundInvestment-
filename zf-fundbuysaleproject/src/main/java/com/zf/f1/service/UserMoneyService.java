package com.zf.f1.service;

import com.zf.c1.vo.JsonResult;

public interface UserMoneyService {

	/**
	 * 得到指定的id用户的金额信息
	 * @param id
	 * @return
	 */
	JsonResult getUserYuEnumber(String id);

	/**
	 * 从小余额转入余额宝
	 * @param moneyIn
	 * @return
	 */
	JsonResult rechargeMoneyFromYuEToYuEBao(Double moneyIn, String id);

	/**
	 * 从余额宝转到余额
	 * @param moneyIn
	 * @param id
	 * @return
	 */
	JsonResult rollOutFromYuEBaoToYuE(Double moneyIn, String id);

}
