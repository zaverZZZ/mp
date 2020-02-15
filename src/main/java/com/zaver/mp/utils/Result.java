package com.zaver.mp.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

	// 响应业务状态 0 成功， -1失败
	private Integer code;

	// 响应消息
	private String msg;

	// 响应中的数据
	private T obj;

	public final static Integer CODE_SUCCESS = 0;
	public final static String MSG_SUCCESS = "success";
	public final static Integer CODE_FAIL = -1;
	public final static String MSG_FAIL = "failed";
	public final static Integer CODE_ERROR_OTHER = 1000;
	public final static String MSG_ERROR_OTHER = "other error";
	public final static Integer CODE_ERROR_PARAM = 1001;
	public final static String MSG_ERROR_PARAM = "param error";
	public final static Integer CODE_ERROR_UNKNOWN = 1002;
	public final static String MSG_ERROR_UNKNOWN = "unknown error";
	public final static Integer CODE_ERROR_IDEMPOTENT = 1003;
	public final static String MSG_ERROR_IDEMPOTENT = "resubmit error";

	public static <T> Result<T> build(Integer status, String msg, T obj) {
		return new Result<T>(status, msg, obj);
	}

	public static <T> Result<T> ok(T obj) {
		return new Result<T>(obj);
	}

	public static <T> Result<T> ok() {
		return new Result<T>(CODE_SUCCESS, MSG_SUCCESS, null);
	}

	public static <T> Result<T> fail() {
		return new Result<T>(CODE_FAIL, MSG_FAIL, null);
	}

	public static <T> Result<T> fail(String msg) {
		return new Result<T>(CODE_FAIL, msg, null);
	}

	public static <T> Result<T> error(Integer code,String msg) {
		return new Result<T>(code, msg, null);
	}

	public static <T> Result<T> error(String msg){
		return error(CODE_FAIL,msg);
	}
	public Result() {

	}

	public static <T> Result<T> build(Integer status, String msg) {
		return new Result<T>(status, msg, null);
	}

	public static <T> Result<T> getResult(T t) {
		Result<T> result = new Result<>(t);
		return result;
	}

	public Result(Integer status, String msg, T obj) {
		this.code = status;
		this.msg = msg;
		this.obj = obj;
	}

	public Result(T obj) {
		this.code = 0;
		this.msg = MSG_SUCCESS;
		this.obj = obj;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}