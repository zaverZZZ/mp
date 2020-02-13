package com.zaver.mp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public class CookieUtil {
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge){
	    try{
			Cookie cookie = new Cookie(name,value);
		    if(maxAge>0)  cookie.setMaxAge(maxAge);
		    URL u = new URL(request.getRequestURL().toString());
//		    logger.info("访问的host : " + u.getHost());
		    cookie.setDomain(u.getHost());
		    cookie.setPath("/");
		    response.addCookie(cookie);
		}catch(Exception ex){
		     logger.error("创建Cookies发生异常！",ex);
		}
	}
	/**
	 * 清空Cookie操作  
	 * clearCookie
	 * @param request
	 * @param response
	 * @return  boolean
	 */
	public static boolean clearCookie(HttpServletRequest request,HttpServletResponse response,String name) {
		boolean bool=false;
		Cookie[] cookies=request.getCookies();  
		try  {  
		     for(int i=0;i<cookies.length;i++)    
		     {  
		      Cookie cookie = new Cookie(name,null);  
		      cookie.setMaxAge(0);  
		      cookie.setPath("/");//根据你创建cookie的路径进行填写      
		      response.addCookie(cookie);
		      bool=true;
		     }  
		}catch(Exception ex){
		     logger.error("清空Cookies发生异常！",ex);
		}
		return bool;
	}
	
	/**
	 * 清空Cookie操作  
	 * clearCookie
	 * @param request
	 * @param response
	 * @return  boolean
	 */
	public static boolean clearCookie(HttpServletRequest request,HttpServletResponse response,String name,String domain) {
		boolean bool=false;
		Cookie[] cookies=request.getCookies();  
		try  
		{  
		     for(int i=0;i<cookies.length;i++)    
		     {  
		      Cookie cookie = new Cookie(name,null);  
		      cookie.setMaxAge(0);  
		      cookie.setPath("/");//根据你创建cookie的路径进行填写      
		      cookie.setDomain(domain);
		      response.addCookie(cookie);
		      bool=true;
		     }  
		}catch(Exception ex){  
		     logger.error("清空Cookies发生异常！",ex);
		}
		return bool;
	}
	/**
	 * 获取指定cookies的值
	 * findCookieByName
	 * @param request
	 * @param name
	 * @return  String
	 */
	public static String findCookieByName(HttpServletRequest request,String name){
		Cookie[] cookies=request.getCookies();  
		String string=null;
		if(cookies==null||cookies.length==0){
			return string;
		}
		try  
		{  
		     for(int i=0;i<cookies.length;i++)    
		     {  
		    	 Cookie cookie=cookies[i];
		     	 String cname=cookie.getName();
		    	 if(!StringUtils.isBlank(cname)&&cname.equals(name)){
		    		 string=cookie.getValue();
		    	 }  
		     }  
		}catch(Exception ex){
		     logger.error("获取Cookies发生异常！",ex);
		}
		return string;
	}
}