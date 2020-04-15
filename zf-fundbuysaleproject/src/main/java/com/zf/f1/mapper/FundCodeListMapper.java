package com.zf.f1.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.c1.pojo.FundCodeList;

public interface FundCodeListMapper  extends BaseMapper<FundCodeList>{
	/**
	 * 得到该fundCode最新的净值
	 * @return
	 */
	double getNewNetWorthForSaleFund(@Param("fundCode") String fundCode);
}
