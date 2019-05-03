package com.zaver.mp.utils.exception;

import java.lang.reflect.InvocationTargetException;

import com.zaver.mp.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName: ExceptionHandle
 * @Description: 全局异常捕获类 
 */
@RestControllerAdvice
public class ExceptionHandle {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
/*IllegalAccessException | InvocationTargetException | NoSuchMethodException*/

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(LocalException.class)
	public Result handlerLocalException(LocalException e){
		log.error(e.getMessage(), e);
		return Result.error(e.getCode(),e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result handlerMissingServletRequestParameterException(MissingServletRequestParameterException e){
		log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_PARAM,Result.MSG_ERROR_PARAM);
	}
	@ExceptionHandler(BindException.class)
	public Result handlerBindException(BindException e){
		log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_PARAM,Result.MSG_ERROR_PARAM);
	}
	/**
	 * 处理shiro异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthorizationException.class)
	public Result handleAuthorizationException(AuthorizationException e){
		log.error(e.getMessage(), e);
		return Result.error("没有权限，请联系管理员授权");
	}

	/**
	 * 其他异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_UNKNOWN,Result.MSG_ERROR_UNKNOWN);
	}
}