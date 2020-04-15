package com.zf.f1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zf.c1.vo.JsonResult;
import com.zf.f1.service.FundBuyOrderService;
import com.zf.f1.service.UserMoneyService;

@RestController
public class UserMoneyController {

	@Autowired
	private UserMoneyService userMoneyService;
	@Autowired
	private FundBuyOrderService fundBuyOrderService;
	
	@GetMapping("/getUserYuEnumber/{id}")
	public JSONPObject insertUserMoney(@PathVariable String id,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = userMoneyService.getUserYuEnumber(id);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
	@GetMapping("/rechargeMoneyFromYuEToYuEBao/{moneyIn}/{id}")
	public JSONPObject rechargeMoneyFromYuEToYuEBao(@PathVariable Double moneyIn,@PathVariable String id,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = userMoneyService.rechargeMoneyFromYuEToYuEBao(moneyIn,id);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
	@GetMapping("/rollOutFromYuEBaoToYuE/{moneyIn}/{id}")
	public JSONPObject rollOutFromYuEBaoToYuE(@PathVariable Double moneyIn,@PathVariable String id,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = userMoneyService.rollOutFromYuEBaoToYuE(moneyIn,id);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
	//id/基金代码/基金净值/净值份额金额/费率 /费率金额
	@GetMapping("/buyFundDetail/{id}/{fundCode}/{fundFullName}/{netWorth}/{shareMoney}/{rate}/{rateNum}/{mny}")
	public JSONPObject buyFundDetail(@PathVariable String id,@PathVariable String fundCode,@PathVariable String fundFullName,@PathVariable Double netWorth
									,@PathVariable Double shareMoney,@PathVariable Double rate,@PathVariable Double rateNum,@PathVariable Double mny,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = fundBuyOrderService.buyFundDetail(id,fundCode,fundFullName,netWorth,shareMoney,rate,rateNum,mny);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
	@GetMapping("/getFundOrder/{id}")
	public JSONPObject getFundOrder(@PathVariable String id,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = fundBuyOrderService.getFundOrder(id);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
	@GetMapping("/opretionSaleDetailFromService/{id}/{code}/{orderId}")
	public JSONPObject opretionSaleDetailFromService(@PathVariable String id,@PathVariable String code,@PathVariable Integer orderId,String callback) {
		JSONPObject jsonpObject = null;
		JsonResult result = fundBuyOrderService.opretionSaleDetailFromService(id,code,orderId);
		jsonpObject = new JSONPObject(callback, result);
		return jsonpObject;
	}
}
