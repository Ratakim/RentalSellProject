package com.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//-------------------------------------------------------------
// public class ViewNameIntercepter
//-------------------------------------------------------------
public class ViewNameInterceptor extends HandlerInterceptorAdapter{
	
	// prevent ~하기전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		System.out.println("컨트롤러에 가기전에 인터셉터가 가로챘습니다.....");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("컨트롤러가 일을 끝냈으므로 인터셉터가 가로챘습니다.....");
		
		// super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
} // END - public class ViewNameIntercepter
