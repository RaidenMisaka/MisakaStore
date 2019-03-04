package cn.misaka.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// `preHandle()`方法是运行在`Controller`之前的，
		// 当该方法返回`false`时，表示拦截，返回`true`时，
		// 表示放行。
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			System.out.println("拦截");
			System.out.println("ContextPath:" + request.getContextPath());
			response.sendRedirect(request.getContextPath()+"/user/login.do");
			return false;
		}
		System.out.println("放行");
		return true;
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
