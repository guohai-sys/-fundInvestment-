package com.zf.c1.util;

public class Assert {
	public static void isNull(Object object,String message) {
		if (object==null||"".equals(object)) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void isEmpty(String string,String message) {
		if (string==null||"".equals(string)) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void isValid(boolean b, String message) {
		// TODO Auto-generated method stub
		if (!b) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void isEmpty(Integer[] arry,String message) {
		if (arry==null||arry.length<=0) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void isEmpty(Integer orderId, String message) {
		// TODO Auto-generated method stub
		if (orderId==null||"".equals(orderId.toString())) {
			throw new IllegalArgumentException(message);
		}
	}
}
