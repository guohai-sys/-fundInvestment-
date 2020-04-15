package com.zf.c1.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 此对象封装控制层的响应结果，包含客户端的数据以及状态码和状态信息
 * @author Tarena
 *
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6034743025092064090L;
	//1 ok 0 error
	private int state=1;
	private String message="ok";
	private Object data;//封装查询或返回的数据
	
	public JsonResult(Object data) {
		this.data=data;
	}
	public JsonResult(RuntimeException e) {
		this.state = 0;
		this.message = e.getMessage();
		this.data = null;
	}
	public JsonResult(Integer state,String message,Object data) {
		this.state=state;
		this.data=data;
		this.message = message;
	}
}
