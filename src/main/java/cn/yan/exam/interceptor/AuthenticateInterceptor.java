package cn.yan.exam.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.yan.exam.common.Constants;
import cn.yan.exam.rest.CodeResult;
import cn.yan.exam.token.TokenDao;
import cn.yan.exam.util.BeanUtil;

/**
 * 登录验证拦截器
 * @author yun
 *
 */
public class AuthenticateInterceptor implements HandlerInterceptor{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TokenDao tokenDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//设置输出编码
		String token = request.getParameter(Constants.KEY_TOKEN);
		logger.debug("获取到的token:" + token);
		//没有附带Token或者token无效
		if(StringUtils.isBlank(token)) {
			writeNoAauthenMsg(response);
			return false;
		}
		//logger.debug("tokenDao:" + tokenDao);
		int userid = tokenDao.getByToken(token);
		//userid = -1;
		if(userid == -1) {
			writeNoAauthenMsg(response);
			return false;
		}
		
		//设置用户Id属性
		request.setAttribute(Constants.KEY_USERID, userid);
		//刷新token过期时间
		tokenDao.refreshToken(token);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	private void writeNoAauthenMsg(HttpServletResponse response) throws IOException {
		CodeResult noAuthenCode = new CodeResult(403,"token无效",null);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(BeanUtil.beanToJson(noAuthenCode).getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
	}

}
