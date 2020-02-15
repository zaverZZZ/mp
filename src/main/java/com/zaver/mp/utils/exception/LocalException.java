package com.zaver.mp.utils.exception;

import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.StringUtils;

/**
 * @ClassName: LocalException
 * @Description: TODO 
 * @author lhb(lnkyliuhb@163.com)
 * @date:2017年11月23日上午10:25:16
 */
public class LocalException extends RuntimeException {
	private Integer code;
	public LocalException(Result result) {
		super(result.getMsg());
		this.code = result.getCode();
	}
	public LocalException(String msg) {
		super(msg);
		this.code = Result.CODE_ERROR_OTHER;
	}
	public LocalException(Integer code,String msg) {
		super(StringUtils.isEmpty(msg) ? "" : msg);
		this.code = code == null ? 1002 : code;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
