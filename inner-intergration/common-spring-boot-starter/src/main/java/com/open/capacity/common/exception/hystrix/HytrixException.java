package com.open.capacity.common.exception.hystrix;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * feign client 避免熔断异常处理
 * blog: https://blog.51cto.com/13005375 
 * code: https://gitee.com/owenwangwen/open-capacity-platform
 */
public class HytrixException extends HystrixBadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437160791033393978L;

	public HytrixException(String msg) {
	  super(msg);
	}

	public HytrixException(Exception e){
	  this(e.getMessage());
	}
}
