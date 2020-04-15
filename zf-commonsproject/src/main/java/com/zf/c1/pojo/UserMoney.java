package com.zf.c1.pojo;

import java.io.Serializable;

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
@TableName("user_money")
public class UserMoney implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1836302350818260541L;
	@TableId
	private String id;			//'手机号/账号'
	private String nickname;	// '昵称'
	private Double balanceMoney; 		//用户余额
	private Double antCheckMoney; 		//花呗余额
	private Double yuebaoMoney; 		//基金余额收入总额（由余额转入，可以提出到余额）
}
