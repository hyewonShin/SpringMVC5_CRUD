package kr.co.hyewon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hyewon.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	//생성자로 bean 주입
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(loginUserBean.isUserLogin() == false) {  // 로그아웃 상태일 때 
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			return false; // 다음 단계로 이동하지 않고 이 단계에서 끝남 
		}
		
		return true; // 로그인 시 다음 단계인 controller로 이동 
	}
	
}
