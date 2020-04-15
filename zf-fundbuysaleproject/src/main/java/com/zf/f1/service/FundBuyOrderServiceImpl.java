package com.zf.f1.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zf.c1.exception.ServiceException;
import com.zf.c1.pojo.FundBuyOrderPojo;
import com.zf.c1.pojo.OrderFallbackLogPojo;
import com.zf.c1.pojo.UserMoney;
import com.zf.c1.util.Assert;
import com.zf.c1.vo.JsonResult;
import com.zf.f1.mapper.FundBuyOrderMapper;
import com.zf.f1.mapper.FundCodeListMapper;
import com.zf.f1.mapper.OrderFallbackLogMapper;
import com.zf.f1.mapper.UserMoneyMapper;

@Service
public class FundBuyOrderServiceImpl implements FundBuyOrderService {

	@Autowired
	private FundBuyOrderMapper fundBuyOrderMapper;
	@Autowired
	private UserMoneyMapper userMoneyMapper;
	@Autowired
	private FundCodeListMapper fundCodeListMapper;
	@Autowired
	private OrderFallbackLogMapper orderFallbackLogMapper;
	
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
	@Override
	@Transactional
	public JsonResult buyFundDetail(String id, String fundCode,String fundFullName, Double netWorth, Double shareMoney, Double rate,
			Double rateNum,Double mny) {
		if (id == null) {
			return new JsonResult(0,"账户id不能为空，请登录",null);
		}
		if (fundCode == null) {
			return new JsonResult(0,"基金代码不能为空",null);
		}
		if (netWorth == null) {
			return new JsonResult(0,"基金净值不能为空",null);
		}
		if (shareMoney == null) {
			return new JsonResult(0,"份额金额不能为空",null);
		}
		if (rate == null) {
			return new JsonResult(0,"买入费率不能为空",null);
		}
		if (rateNum == null) {
			return new JsonResult(0,"买入费不能为空",null);
		}
		
		//得到该用户的金额信息,减去该用户的余额宝金额，修改基金的fund不能为负数
		UserMoney userMoney = userMoneyMapper.selectById(id);
		double money = userMoney.getYuebaoMoney()-mny;
		userMoney.setYuebaoMoney(money);
		int rows = userMoneyMapper.updateById(userMoney);
		if (rows == 0) {
			return new JsonResult(0,"买入失败,余额宝扣减失败,请重新买入",userMoney);
		}
		Date date = new Date();
		//自1970年1月1日00:00:00 GMT以后time毫秒数设置时间和日期。‘
		long time = date.getTime();
		Date saleDate = new Date();
		saleDate.setTime(time+4*24*60*60*1000);
		FundBuyOrderPojo fbo = new FundBuyOrderPojo();
		
		double share = Double.parseDouble(new DecimalFormat("0.00").format(shareMoney/netWorth));
		fbo.setId(id).setStatus(0).setFundCode(fundCode).setFundFullName(fundFullName).setNetWorth(netWorth).setShareMoney(shareMoney).setShare(share)
			.setRate(rate).setRateNum(rateNum).setMnyMoney(mny).setSaleDate(saleDate).setCreateDate(date).setUpDate(date);
		
		int row = fundBuyOrderMapper.insert(fbo);
		if (row == 0) {
			throw new ServiceException("买入失败,请重新买入");
		}
		return new JsonResult(1,"买入成功",fbo);
	}
	@Override
	public JsonResult getFundOrder(String id) {
		Assert.isEmpty(id, "用户id不能为空");
		QueryWrapper<FundBuyOrderPojo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",id);
		List<FundBuyOrderPojo> list = fundBuyOrderMapper.selectList(queryWrapper);
		if (list == null) {
			throw new ServiceException("该用户没有基金订单");
		}
		/* list.forEach((action)->System.out.println(action)); */
		return  new JsonResult(1,"查找成功",list);
	}
	@Override
	@Transactional
	public JsonResult opretionSaleDetailFromService(String id, String code,Integer orderId) {
		Assert.isEmpty(id, "用户id不能为空");
		Assert.isEmpty(code, "赎回基金代码不能为空");
		Assert.isEmpty(orderId, "赎回基金代码不能为空");

		//该基金订单
		FundBuyOrderPojo order = fundBuyOrderMapper.selectById(orderId);
		//份额
		Double share = order.getShare();
		//该基金的最新净值
		double newNetWorth = fundCodeListMapper.getNewNetWorthForSaleFund(code);
		//赎回总金额
		Double redeemSum = Double.parseDouble(new DecimalFormat("0.00").format(share*newNetWorth));
		//赎回费率
		double redeemRate = calculateRedeemRate(order.getCreateDate());
		System.out.println("税率："+redeemRate);
		//赎回手续费
		double redeemServiceCharge = Double.parseDouble(new DecimalFormat("0.00").format(redeemSum*redeemRate));
		//赎回净额
		double netAmount =  Double.parseDouble(new DecimalFormat("0.00").format(redeemSum-redeemServiceCharge));
		//净收益
		double netEarning = Double.parseDouble(new DecimalFormat("0.00").format(netAmount - order.getMnyMoney()));
		//日志对象
		OrderFallbackLogPojo ofl = new OrderFallbackLogPojo();
		
		ofl.setNetWorth(newNetWorth).setShare(share).setRedeemSum(redeemSum).setRedeemRate(redeemRate).setRedeemServiceCharge(redeemServiceCharge)
								.setNetAmount(netAmount).setMnyMoney(order.getMnyMoney()).setNetEarning(netEarning).setCreateTimeDate(new Date());
		
		int row = orderFallbackLogMapper.insert(ofl);
		if (row == 0 ) {
			throw new ServiceException("赎回失败,系统错误,请稍后再试");
		}
		
		UserMoney userMoney = userMoneyMapper.selectById(id);
		userMoney.setYuebaoMoney(Double.parseDouble(new DecimalFormat("0.00").format(userMoney.getYuebaoMoney()+netAmount)));
		int rows = userMoneyMapper.updateById(userMoney);
		
		if (rows == 0 ) {
			throw new ServiceException("赎回失败,系统错误,请稍后再试");
		}
		
		//修改order订单的status 0-1
		order.setStatus(1);
		int rowss = fundBuyOrderMapper.updateById(order);
		if (rowss == 0 ) {
			throw new ServiceException("赎回失败,系统错误,请稍后再试");
		}
		
		//System.out.println(order.toString());
		return  new JsonResult(1,"赎回成功",ofl);
	}
	private double calculateRedeemRate(Date date) {
		long oldTime = date.getTime();//创建的日期
		long newTime = new Date().getTime();//今日的日期
		int day = (int) (newTime - oldTime)/(24*60*60*1000);
		System.out.println("一共："+day+"天");
		return getRate(day);//得到税率
	}
	private double getRate(int day) {
		if (day >= 0 & day < 7) {
			return 0.0150;
		}else if (day >= 7 & day < 30) {
			return 0.0075;
		}else if (day >= 30 & day < 365) {
			return 0.0050;
		}else {
			return 0;
		}
	}
}





















































