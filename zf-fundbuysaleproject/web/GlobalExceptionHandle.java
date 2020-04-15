package com.hs.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hs.common.vo.JsonResult;
@RestControllerAdvice
public class GlobalExceptionHandle {

		@ExceptionHandler(RuntimeException.class)
		public JsonResult doHandleRunnableException(RuntimeException e) {
			e.printStackTrace();
			return new JsonResult(e);
		}

}