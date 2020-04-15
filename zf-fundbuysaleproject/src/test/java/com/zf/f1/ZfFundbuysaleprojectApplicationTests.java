package com.zf.f1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zf.c1.vo.JsonResult;
import com.zf.f1.service.UserMoneyService;

@SpringBootTest
class ZfFundbuysaleprojectApplicationTests {
	@Autowired
	UserMoneyService userMoneyService;
	@Test
	void contextLoads() {
		JsonResult r = userMoneyService.getUserYuEnumber("15021946185");
		System.out.println(r);
		
	}

}
