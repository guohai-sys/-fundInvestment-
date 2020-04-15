package com.zf.f1.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zf.c1.vo.JsonResult;

@RestControllerAdvice
public class GlobalExceptionHandle {

		@ExceptionHandler(RuntimeException.class)
		public Object doHandleRunnableException(RuntimeException e,HttpServletRequest request) {
			String callback = request.getParameter("callback");
			if(!StringUtils.isEmpty(callback)) {
				e.printStackTrace();
				//用户发起的JSONP的请求.
				return new JSONPObject(callback, new JsonResult(0,e.getMessage(),null));
			}
			e.printStackTrace();
			return new JsonResult(e);
		}

}