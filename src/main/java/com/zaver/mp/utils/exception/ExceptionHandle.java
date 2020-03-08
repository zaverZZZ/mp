package com.zaver.mp.utils.exception;

import com.zaver.mp.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName: ExceptionHandle
 * @Description: 全局异常捕获类 
 */
@RestControllerAdvice
public class ExceptionHandle {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
	// 是否打印log.error
	private static final boolean logFlag = true;

	// 处理自定义异常
	@ExceptionHandler(LocalException.class)
	public Result handlerLocalException(LocalException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error(e.getCode(),e.getMessage());
	}
	// request参数丢失
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public Result handlerMissingServletRequestParameterException(MissingServletRequestParameterException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_PARAM,Result.MSG_ERROR_PARAM);
	}
	// 参数错误
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public Result handlerHttpMessageNotReadableException(HttpMessageNotReadableException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_PARAM,Result.MSG_ERROR_PARAM);
	}
	// api参数绑定错误
	@ExceptionHandler(BindException.class)
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public Result handlerBindException(BindException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_PARAM,Result.MSG_ERROR_PARAM);
	}
	// 处理shiro异常
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Result handlerAuthorizationException(AuthorizationException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error("没有权限，请联系管理员授权");
	}

    @ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public Result handlerUnauthorizedException(UnauthorizedException e){
        if (logFlag) log.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Result handlerHttpRequestMethodNotSupportedExceptionException(HttpRequestMethodNotSupportedException e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error("请求method错误");
	}
	// 其他异常
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Result handlerException(Exception e){
		if (logFlag) log.error(e.getMessage(), e);
		return Result.error(Result.CODE_ERROR_UNKNOWN,Result.MSG_ERROR_UNKNOWN);
	}
}