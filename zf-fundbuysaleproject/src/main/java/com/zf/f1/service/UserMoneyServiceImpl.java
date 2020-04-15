package com.zf.f1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zf.c1.pojo.UserMoney;
import com.zf.c1.util.Assert;
import com.zf.c1.vo.JsonResult;
import com.zf.f1.mapper.UserMoneyMapper;

@Service
public class UserMoneyServiceImpl implements UserMoneyService {

	@Autowired
	private UserMoneyMapper userMoneyMapper;
	
	@Override
	public JsonResult getUserYuEnumber(String id) {
		Assert.isEmpty(id, "账号不能为空");
		UserMoney rs = userMoneyMapper.selectById(id);
		if (rs == null) {
			return new JsonResult(0,"查询用户金额失败,可能时用户不存在或者服务器异常",null);
		}
		return new JsonResult(1,"查找成功",rs);
	}
	@Override
	@Transactional
	public JsonResult rechargeMoneyFromYuEToYuEBao(Double moneyIn, String id) {
		Assert.isNull(moneyIn, "转入金额不能为空");
		Assert.isNull(id, "账户不能为空");
		UserMoney rs = userMoneyMapper.selectById(id);
		if (rs == null) {
			return new JsonResult(0,"查询用户金额失败,可能时用户不存在或者服务器异常",null);
		}
		Double balanceMoney = rs.getBalanceMoney();
		Double d1 = balanceMoney-moneyIn;
		if (d1 < 0) {
			return new JsonResult(0,"输入的金额大于实际金额,请重新输入",null);
		}
		rs.setYuebaoMoney(rs.getYuebaoMoney()+moneyIn);
		rs.setBalanceMoney(d1);
		userMoneyMapper.updateById(rs);
		return new JsonResult(1,"转入成功",rs);
	}
	@Override
	@Transactional
	public JsonResult rollOutFromYuEBaoToYuE(Double moneyIn, String id) {
		Assert.isNull(moneyIn, "转入金额不能为空");
		Assert.isNull(id, "账户不能为空");
		UserMoney rs = userMoneyMapper.selectById(id);
		Double yuebaoMoney = rs.getYuebaoMoney();
		Double d1 = yuebaoMoney-moneyIn;
		if (d1 < 0) {
			return new JsonResult(0,"输入的金额大于实际余额宝金额,请重新输入",null);
		}
		rs.setBalanceMoney(rs.getBalanceMoney()+moneyIn);
		rs.setYuebaoMoney(d1);
		userMoneyMapper.updateById(rs);
		
		return new JsonResult(1,"转出成功",rs);
	}

}






















