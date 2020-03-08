package com.zaver.mp.utils.aspect;

import com.alibaba.fastjson.JSON;
import com.zaver.mp.sys.rbac.model.LoginForm;
import com.zaver.mp.utils.HttpUtil;
import com.zaver.mp.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: LogAspect 
* @Description: 日志记录AOP实现 
*
 */
@Aspect
@Component
//@Scope("prototype")
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	private String requestPath = null ;		 			// 请求地址
	private String userInfo = null ; 					// 用户名
	private Map<?,?> inputParamMap = null ; 			// 传入参数
	private Map<String, Object> outputParamMap = null; 	// 存放输出结果
	private long startTimeMillis = 0; 					// 开始时间
	private long endTimeMillis = 0; 					// 结束时间
	private String method = null;    					// post get
	/**
	 * 
	 * @Title：doBeforeInServiceLayer
	 * @Description: 方法调用前触发 
	 *  记录开始时间 
	 * @param joinPoint
	 */
	@Before("execution(* com.zaver.mp.*.*.controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
	}

	/**
	 * 
	 * @Title：doAfterInServiceLayer
	 * @Description: 方法调用后触发 
	 *  记录结束时间
	 * @param joinPoint
	 */
	@After("execution(* com.zaver.mp.*.*.controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
		this.printOptLog();
	}

	/**
	 * 
	 * @Title：doAround
	 * @Description: 环绕触发 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.zaver.mp.*.*.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		/**
		 * 1.获取request信息
		 * 2.根据request获取session
		 * 3.从session中取出登录用户信息
		 */
		HttpServletRequest request = HttpUtil.getHttpServletRequest();
		Object[] args = pjp.getArgs(); // 参数值
		String[] argNames = ((MethodSignature)pjp.getSignature()).getParameterNames(); // 参数名
		Map<String,Object> map = new HashMap<>();
		if(args.length!=0 && argNames.length!=0 && argNames.length==args.length){
			for (int i = 0; i < argNames.length; i++) {
				String argName = argNames[i];
				Object arg = args[i];
				//TODO 防止打印password的暂行办法
				if("loginForm".equals(argName)){
                    LoginForm form = (LoginForm) arg;
                    userInfo = form.getUsername();
                    LoginForm logForm = new LoginForm();
                    logForm.setUsername(form.getUsername());
                    logForm.setPassword("***************");
                    map.put(argName,logForm);
                    continue;
                }
				map.put(argName,arg);
			}
		}
		String requestURI = request.getRequestURI();  //获取访问URI
		// 从请求中获取用户信息
        //TODO 用户信息暂时空缺
		userInfo = StringUtils.isEmpty(userInfo) ? "" : userInfo;
		// 获取输入参数
		inputParamMap = map;
		// inputParamMap = request.getParameterMap();
		// 获取请求地址
		requestPath = request.getServletPath();//.replaceAll("/xcxWeb", "");
		// 获取post get
		method = request.getMethod();
		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		outputParamMap = new HashMap<>();
		Object result = pjp.proceed();// result的值就是被拦截方法的返回值
		outputParamMap.put("result", result);
		return result;
	}
	
	/**
	 * 
	 * @Title：printOptLog
	 * @Description: 输出日志 
	 */
	private void printOptLog() {
		String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
		String aboutUser = "\n- user： "+userInfo;
		String aboutRequest = "\n- URI："+requestPath+"; op_time：" + optTime + " pro_time：" + (endTimeMillis - startTimeMillis) + "ms ;";
		String aboutMethod = "\n- method : " + method;
		String aboutparam = "\n- param："+JSON.toJSONString(inputParamMap)+";";
		String aboutResponse = "\n- result："+JSON.toJSONString(outputParamMap);
		String splitLine = "\n----------------------------------------------------------------------------------------------";
		logger.info(aboutUser + aboutRequest + aboutMethod + aboutparam + aboutResponse + splitLine);
	}
}
